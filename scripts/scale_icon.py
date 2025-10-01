#!/usr/bin/env python3
"""
Decode the base64 PNG at app/tmp/ic_launcher_base64.txt and generate
mipmap density PNGs under app/src/main/res/mipmap-*/ic_launcher.png.

This script uses Pillow. It will exit with non-zero status on error
and print a short message describing the failure.
"""
import base64
import binascii
import os
import sys
from PIL import Image, UnidentifiedImageError

BASE64_PATH = 'app/tmp/ic_launcher_base64.txt'
OUT_BASE = 'app/src/main/res'
SIZES = {
    'mdpi': 48,
    'hdpi': 72,
    'xhdpi': 96,
    'xxhdpi': 144,
    'xxxhdpi': 192,
}


def ensure_dirs():
    for k in SIZES.keys():
        path = os.path.join(OUT_BASE, f'mipmap-{k}')
        os.makedirs(path, exist_ok=True)


def decode_to_src(src_path):
    try:
        with open(BASE64_PATH, 'rb') as f:
            data = f.read()
        # Be tolerant: some uploads include extra characters or ellipses.
        # Keep only valid base64 alphabet characters and repair padding.
        try:
            text = data.decode('utf-8', errors='ignore')
        except Exception:
            text = str(data)
        # valid base64 chars
        valid_chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/='
        cleaned = ''.join(ch for ch in text if ch in valid_chars)
        # remove any trailing stray dots/ellipses introduced by truncation markers
        cleaned = cleaned.replace('...', '')
        # fix padding
        pad = len(cleaned) % 4
        if pad != 0:
            cleaned += '=' * (4 - pad)
        try:
            decoded = base64.b64decode(cleaned)
        except binascii.Error as e:
            # fallback: try raw decode of original bytes
            decoded = base64.b64decode(data)
        with open(src_path, 'wb') as out:
            out.write(decoded)
    except Exception as e:
        print('ERROR decoding base64:', e)
        sys.exit(1)


def generate_sizes(src_path):
    try:
        # Verify PIL can open the image
        img = Image.open(src_path)
        img.load()
    except UnidentifiedImageError as e:
        print('ERROR: PIL cannot identify image:', e)
        sys.exit(2)
    except Exception as e:
        print('ERROR opening image:', e)
        sys.exit(3)

    for k, s in SIZES.items():
        out_path = os.path.join(OUT_BASE, f'mipmap-{k}', 'ic_launcher.png')
        try:
            resized = img.resize((s, s), Image.LANCZOS)
            resized.save(out_path)
        except Exception as e:
            print(f'ERROR saving {out_path}:', e)
            sys.exit(4)


def main():
    ensure_dirs()
    src = os.path.join(OUT_BASE, 'mipmap-xxxhdpi', 'ic_launcher.png')
    decode_to_src(src)
    generate_sizes(src)
    print('OK: mipmaps generated')


if __name__ == '__main__':
    main()
