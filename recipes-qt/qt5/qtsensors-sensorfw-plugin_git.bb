SUMMARY = "Sensorfw plugin for qtsensors"
LICENSE = "LGPL-2.1-only | LGPL-3.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=cff17b12416c896e10ae2c17a64252e7 \
    file://LICENSE.LGPLv3;md5=c1939be5579666be947371bc8120425f \
    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
"

DEPENDS = "qtbase qtsensors sensorfw"
RDEPENDS:${PN} = "sensorfw bash"

# We're depending on sensorfw so need to be MACHINE_ARCH
PACKAGE_ARCH = "${MACHINE_ARCH}"

PV = "5.15+git${SRCPV}"
SRCREV = "9414e7e35510fbcb1c07172c143b8a52c403caba"

inherit qmake5
inherit pkgconfig


SRC_URI = "git://github.com/webOS-ports/qtsensors-sensorfw.git;protocol=https;branch=master"
S = "${WORKDIR}/git"

do_install:append() {
    if [ "${sysconfdir}" != "${OE_QMAKE_PATH_QT_SETTINGS}" ] ; then
        cp -R --no-dereference --preserve=mode,links -v ${D}${sysconfdir}/xdg ${D}${OE_QMAKE_PATH_QT_SETTINGS}
    fi
}

FILES:${PN} += " \
    ${libdir} \
    xdg/QtProject/Sensors.conf \
"
