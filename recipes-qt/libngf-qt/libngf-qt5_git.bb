SUMMARY = "Qt-based client library for Non-Graphic Feedback daemon"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "0.8.2+git${SRCPV}"
SRCREV = "503e9d0714f9fd1bcbd86c5968b90bc9d47479a0"

DEPENDS = "qtbase qtdeclarative qtfeedback dbus"
RDEPENDS:${PN} = "qtbase qtdeclarative qtfeedback dbus ngfd"

SRC_URI = " \
    git://github.com/sailfishos/libngf-qt.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig

EXTRA_QMAKEVARS_PRE += "PREFIX=${prefix}"

B = "${S}"

do_install:append() {
    rm -r ${D}/opt
}

FILES:${PN} += " \
    ${libdir}/libngf-qt.so.* \
    ${libdir}/qml/Nemo/Ngf/* \
    ${libdir}/qml/org/nemomobile/ngf/* \
    ${libdir}/plugins/feedback/libqtfeedback_libngf.so \
    ${libdir}/plugins/feedback/libngf.json \
"

FILES:${PN}-dev += " \ 
    ${includedir}/ngf-qt5/*.h \
    ${libdir}/libngf-qt.so \
    ${libdir}/pkgconfig/ngf-qt5.pc \
"
