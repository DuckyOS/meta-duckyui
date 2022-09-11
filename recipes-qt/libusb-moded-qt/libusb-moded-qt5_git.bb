SUMMARY = "A library of Qt5 bindings for usb_moded"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.BSD;md5=caa037e0975ee5862b72644673e7590c"

PV = "1.10+git${SRCPV}"
SRCREV = "52b2f6b3dacee0b104d73b7f3d932d38a30499d7"

DEPENDS = "qtbase usb-moded dbus"
RDEPENDS:${PN} = "qtbase usb-moded dbus"

SRC_URI = " \
    git://github.com/sailfishos/libusb-moded-qt.git;protocol=https;branch=master \
"
S = "${WORKDIR}/git"

EXTRA_QMAKEVARS_PRE += "PREFIX=${prefix}"

inherit qmake5 pkgconfig

FILES:${PN} += " \
    ${libdir}/libusb-moded-qt5.so.* \
"

FILES:${PN}-dev += " \ 
    ${includedir}/usb-moded-qt5/*.h \
    ${libdir}/libusb-moded-qt5.so \
    ${libdir}/pkgconfig/usb-moded-qt5.pc \
"
