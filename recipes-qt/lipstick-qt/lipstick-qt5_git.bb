SUMMARY = "QML toolkit for homescreen creation"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=fbc093901857fcd118f065f900982c24"

PV = "0.34.47+git${SRCPV}"
SRCREV_FORMAT = "dbus-glib"
SRCREV = "70afa6b96c98f715c8b16f7a64896930dd4d23f1"
SRCREV_dbus-glib = "d42176ae4763e5288ef37ea314fe58387faf2005"

DEPENDS = "qtbase qtdeclarative qtxmlpatterns mlite-qt5 mce-headers libmce-qt5 \
    nemo-keepalive libdsme libusb-moded-qt5 dbus libresourceqt-qt5 libngf-qt5 \
    nemo-qml-plugin-systemsettings-qt5 nemo-qml-plugin-devicelock-qt5 glib-2.0 \
    qtwayland qtwayland-native doxygen-native qtsensors qttools-native qtsystems"
RDEPENDS:${PN} = "mce"

SRC_URI = " \
    git://github.com/DuckyOS/lipstick.git;protocol=https;branch=master \
    git://github.com/sailfishos-mirror/dbus-glib.git;protocol=https;branch=master;destsuffix=git/src/3rdparty/dbus-gmain;name=dbus-glib \
"

S = "${WORKDIR}/git"

inherit qmake5 pkgconfig

B = "${S}"

FILES:${PN} += " \
    ${libdir}/liblipstick-qt5.so.* \
    ${libdir}/qml/org/nemomobile/lipstick \
    ${sysconfdir}/dbus-1/system.d/lipstick.conf \
    ${datadir}/translations/*.qm \
    ${datadir}/translations/source \
    ${datadir}/lipstick \
"

FILES:${PN}-dev += " \ 
    ${includedir}/lipstick \
    ${libdir}/liblipstick-qt5.so \
    ${libdir}/liblipstick-qt5.prl \
    ${libdir}/pkgconfig/lipstick.pc \
"
