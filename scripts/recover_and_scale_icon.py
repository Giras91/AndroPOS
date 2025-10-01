#!/usr/bin/env python3
"""
Scan the possibly-corrupted base64 file and try to recover a valid PNG segment.
If found, save it to app/src/main/res/mipmap-xxxhdpi/ic_launcher.png and invoke
the existing scripts/scale_icon.py to generate the other densities.

This is automatic and will print progress and final status.
"""
import base64
import binascii
import os
import subprocess
import sys

BASE64_PATH = 'app/tmp/ic_launcher_base64.txt'
OUT_PATH = 'app/src/main/res/mipmap-xxxhdpi/ic_launcher.png'

def read_and_clean():
    data = open(BASE64_PATH, 'rb').read()
    try:
        text = data.decode('utf-8', errors='ignore')
    except Exception:
        text = str(data)
    valid = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/='
    cleaned = ''.join(ch for ch in text if ch in valid)
    cleaned = cleaned.replace('...', '')
    return cleaned

def try_decode_segment(seg):
    try:
        decoded = base64.b64decode(seg)
    except Exception:
        return None
    if len(decoded) < 8:
        return None
    # PNG signature
    if decoded[:8] != b'\x89PNG\r\n\x1a\n':
        return None
    # basic sanity: contains IEND chunk
    if b'IEND' not in decoded:
        return None
    return decoded

def scan_for_png(s):
    n = len(s)
    print(f'cleaned base64 length: {n}')
    # try reasonable window sizes; prefer larger windows but check multiples of 4
    # We'll try starts in steps of 1, and ends in steps of 4
    max_len = min(n, 800000)
    for start in range(0, n - 24):
        # early skip if remaining too small
        rem = n - start
        if rem < 24:
            break
        # try some increasing end sizes, starting from small to larger
        for end in range(start + 24, min(n, start + 200000), 4):
            seg = s[start:end]
            decoded = try_decode_segment(seg)
            if decoded:
                print(f'Found PNG at base64[{start}:{end}] length {end-start} -> bytes {len(decoded)}')
                return decoded
        # optional: print progress every 10000 starts
        if start % 10000 == 0 and start > 0:
            print(f'scanned start {start}/{n}')
    return None

def main():
    if not os.path.exists(BASE64_PATH):
        print('Base64 file not found:', BASE64_PATH)
        sys.exit(1)
    s = read_and_clean()
    png = scan_for_png(s)
    if not png:
        print('No valid PNG found in base64 content')
        sys.exit(2)
    os.makedirs(os.path.dirname(OUT_PATH), exist_ok=True)
    with open(OUT_PATH, 'wb') as f:
        f.write(png)
    print('Wrote recovered PNG to', OUT_PATH)
    # Run the scaler script
    try:
        subprocess.check_call([sys.executable, 'scripts/scale_icon.py'])
    except subprocess.CalledProcessError as e:
        print('Scaler script failed with exit', e.returncode)
        sys.exit(3)
    print('Recovery + scaling completed successfully')

if __name__ == '__main__':
    main()
