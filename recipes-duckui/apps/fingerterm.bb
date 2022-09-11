SUMMARY = "A terminal emulator with a custom virtual keyboard"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "1.4.6+git${SRCPV}"
SRCREV = "d9224c3b65803f2a51853c5e271377cc7ed42b32"

DEPENDS = "qtbase qtdeclarative qttools-native qtxmlpatterns"
RDEPENDS:${PN} = "ttf-liberation-mono qtxmlpatterns"

SRC_URI = " \
    git://github.com/sailfishos/fingerterm.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

EXTRA_QMAKEVARS_PRE = "\
    DEFAULT_FONT=LiberationMono \
"

inherit qmake5

FILES:${PN} += "${datadir}/translations"
