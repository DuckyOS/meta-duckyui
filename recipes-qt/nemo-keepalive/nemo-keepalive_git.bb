SUMMARY = "CPU and display keepalive and scheduling library"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://license.lgpl;md5=4fbd65380cdd255951079008b364516c"

PV = "1.8.7+git${SRCPV}"
SRCREV_FORMAT = "dbus-glib"
SRCREV = "1bb68f25c7fd174b787399ebc0510d47a3ed1103"
SRCREV_dbus-glib = "d42176ae4763e5288ef37ea314fe58387faf2005"

DEPENDS = "qtbase qtdeclarative mce-headers libiphb doxygen-native"
RDEPENDS:${PN} = "qtbase qtdeclarative libiphb"

SRC_URI = " \
    git://github.com/sailfishos/nemo-keepalive.git;protocol=https;branch=master \
    git://github.com/sailfishos-mirror/dbus-glib.git;protocol=https;branch=master;destsuffix=git/dbus-gmain;name=dbus-glib \
"
S = "${WORKDIR}/git"

inherit qmake5 pkgconfig

do_configure:prepend() {
    # Fix the call to qdbusxml2cpp.
    sed "s/\\\$\\\$\[QT_INSTALL_BINS\]\///g" -i "${S}/lib/lib.pro"

    # Fix the generation of tests.xml.
    sed "s/ tests\.xml/ \\\$\\\$PWD\/tests\.xml/g" -i "${S}/tests/tests.pro"
}

do_compile:append() {
    oe_runmake -C "${S}/lib-glib" VERS=1.8.7 _LIBDIR=${libdir}
    oe_runmake -C "${S}/tools" VERS=1.8.7 _LIBDIR=${libdir}
}

do_install:append() {
    oe_runmake -C "${S}/lib-glib" install ROOT=${D} VERS=1.8.7 _LIBDIR=${libdir}
    oe_runmake -C "${S}/tools" install ROOT=${D} VERS=1.8.7 _LIBDIR=${libdir}
    rm -rf "${D}/opt"
}

FILES:${PN} += " \
    ${libdir}/libkeepalive.so.* \
    ${libdir}/libkeepalive-glib.so.* \
    ${libdir}/qml/Nemo/KeepAlive/* \
    ${datadir}/examples/keepalive/*.qml \
"

FILES:${PN}-dev += " \ 
    ${libdir}/libkeepalive.so \
    ${libdir}/pkgconfig/keepalive.pc \
    ${includedir}/keepalive/*.h \
    ${libdir}/libkeepalive-glib.so \
    ${libdir}/pkgconfig/keepalive-glib.pc \
    ${includedir}/keepalive-glib/*.h \
"
