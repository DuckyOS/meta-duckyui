SUMMARY = "Library for input/ouput data"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "0.19.12+git${SRCPV}"
SRCREV = "f9dea54e70feb352d9a16a2d87757e7bbe3a8daf"

DEPENDS = "qtbase-native openssl-native bison-native flex-native"
RDEPENDS:${PN} = "qtbase-native openssl-native"

SRC_URI = " \
    git://github.com/sailfishos/libiodata.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig native

B = "${S}"

PARALLEL_MAKE = "-j1"

do_install:append() {
    mv ${D}/${datadir}/mkspecs ${D}/${libdir}/
    mv ${D}/usr/share/iodata-qt5-tests ${D}/${datadir}/
    mv ${D}/usr/include/* ${D}/${includedir}/
    mkdir -p ${D}/${bindir}/
    mv ${D}/usr/bin/* ${D}/${bindir}/
}

FILES:${PN} += " \
    ${libdir}/libiodata-qt5.so.* \
    ${bindir}/iodata-qt5-type-to-c++ \
"

FILES:${PN}-dev += " \ 
    ${includedir}/iodata-qt5/*.h \
    ${libdir}/libiodata-qt5.so \
    ${libdir}/mkspecs/features/iodata-qt5.prf \
    ${datadir}/iodata-qt5-tests/tests.xml \
"
