SUMMARY = "Nemo QML models plugin"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=cb8aedd3bced19bd8026d96a8b6876d7"

PV = "0.2.4+git${SRCPV}"
SRCREV = "b8d455b51c6d686bc310fc86ce22094df98f0056"

DEPENDS = "qtbase qtdeclarative libmlocale-qt5"

SRC_URI = " \
    git://github.com/sailfishos/nemo-qml-plugin-devicelock.git;protocol=https;branch=master \
"

inherit qmake5 pkgconfig

S = "${WORKDIR}/git"
B = "${S}"

do_install:append() {
    mkdir ${D}/lib
    mv ${D}/usr/lib/systemd ${D}/lib/
}

FILES:${PN} += " \
    ${libdir}/libnemomodels-qt5.so.* \
    ${libdir}/qml/org/nemomobile/models/* \
"

FILES:${PN}-dev += " \ 
    ${libdir}/libnemomodels-qt5.prl \
    ${libdir}/pkgconfig/nemomodels-qt5.pc \
    ${includedir}/nemomodels-qt5/* \
"
