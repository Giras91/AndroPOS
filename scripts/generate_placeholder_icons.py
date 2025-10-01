#!/usr/bin/env python3
"""
Generate simple placeholder launcher PNGs for mdpi/hdpi/xhdpi/xxhdpi/xxxhdpi.
This is used only when the real icon PNG cannot be recovered. The script
produces colored squares with the app initial 'E' centered.
"""
from PIL import Image, ImageDraw, ImageFont
import os

OUT_BASE = 'app/src/main/res'
SIZES = {
    'mdpi': 48,
    'hdpi': 72,
    'xhdpi': 96,
    'xxhdpi': 144,
    'xxxhdpi': 192,
}

def ensure_dirs():
    for k in SIZES:
        os.makedirs(os.path.join(OUT_BASE, f'mipmap-{k}'), exist_ok=True)

def make_icon(size, out_path):
    img = Image.new('RGBA', (size, size), (98, 0, 238, 255))
    draw = ImageDraw.Draw(img)
    try:
        font = ImageFont.truetype('DejaVuSans-Bold.ttf', int(size*0.5))
    except Exception:
        font = ImageFont.load_default()
    try:
        bbox = draw.textbbox((0,0), 'E', font=font)
        w = bbox[2]-bbox[0]
        h = bbox[3]-bbox[1]
    except Exception:
        try:
            w,h = font.getsize('E')
        except Exception:
            w,h = (int(size*0.5), int(size*0.5))
    draw.text(((size-w)/2,(size-h)/2), 'E', font=font, fill=(255,255,255,255))
    img.save(out_path)

def main():
    ensure_dirs()
    for k,s in SIZES.items():
        out = os.path.join(OUT_BASE, f'mipmap-{k}', 'ic_launcher.png')
        make_icon(s, out)
        print('Wrote', out)

if __name__ == '__main__':
    main()
