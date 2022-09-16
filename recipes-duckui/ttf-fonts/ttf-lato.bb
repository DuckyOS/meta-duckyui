require ttf.inc

SUMMARY = "Lato fonts"
HOMEPAGE = "https://fonts.google.com/specimen/Lato"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://OFL.txt;md5=f121d7ae3e488f62d05c62f487fe7508"

SRC_URI = "https://www.latofonts.com/download/lato2ofl.zip"
SRC_URI[sha256sum] = "42b54e96c07e299d967fc3227c7bd63a20d6cfb1dc8fd6dae83628091e20a5b8"

S = "${WORKDIR}/Lato2OFL"

FILES:${PN} = "${datadir}/fonts/truetype/*.ttf"
