SUMMARY = "Sailfish Access Control library"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://../../LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de"

PV = "0.0.7+git${SRCPV}"
SRCREV = "5bc93590910c71245d5e2866ef0cbb00a3af2df6"

DEPENDS = "qtbase qtdeclarative glib-2.0"
RDEPENDS:${PN} = "qtbase qtdeclarative glib-2.0"

SRC_URI = " \
    git://github.com/sailfishos/sailfish-access-control.git;protocol=https;branch=master \
    file://LICENSE.LGPL \
"
S = "${WORKDIR}/git/glib"

inherit pkgconfig

do_build() {
    oe_runmake LIBDIR=${libdir} ROOT=${D} VERSION=0.0.7
    oe_runmake LIBDIR=${libdir} ROOT=${D} VERSION=0.0.7 sailfishaccesscontrol.pc
}

do_install() {
    oe_runmake LIBDIR=${libdir} ROOT=${D} install-libsailfishaccesscontrol
    oe_runmake LIBDIR=${libdir} ROOT=${D} install-libsailfishaccesscontrol-dev
}

FILES:${PN} += " \
    ${libdir}/libsailfishaccesscontrol.so.* \
"

FILES:${PN}-dev += " \ 
    ${includedir}/sailfishaccesscontrol/*.h \
    ${libdir}/libsailfishaccesscontrol.so \
    ${libdir}/pkgconfig/sailfishaccesscontrol.pc \
"
