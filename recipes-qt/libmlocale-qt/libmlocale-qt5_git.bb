SUMMARY = "Contains classes MLocale and friends originally from libmeegotouch"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c"

PV = "0.7.6+git${SRCPV}"
SRCREV = "7c62d5532cbe3f0f9be612becbebfb524b0a3767"

DEPENDS = "qtbase qtxmlpatterns icu"

SRC_URI = " \
    git://github.com/sailfishos/libmlocale.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig

B = "${S}"

FILES:${PN} += " \
    ${libdir}/*.so.* \
"

FILES:${PN}-dev += " \ 
    ${includedir}/mlocale5/* \
    ${libdir}/*.prl \
    ${libdir}/*.so \
    ${libdir}/pkgconfig/*.pc \
    ${datadir}/mkspecs/features/*.prf \
"
