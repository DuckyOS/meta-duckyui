# Enable accessibility for qtquickcontrols
PACKAGECONFIG:append = " accessibility"

# Configure to use platform harfbuzz
PACKAGECONFIG:append = " harfbuzz"

# Configure to compile with GL ES2 instead of default desktop GL
PACKAGECONFIG:append = " gles2 eglfs"
PACKAGECONFIG:remove = "gl"
PACKAGECONFIG:append:qemuall = " kms gbm"
PACKAGECONFIG:append:hammerhead = " kms gbm"
PACKAGECONFIG:append:pinephone = " kms gbm"
PACKAGECONFIG:append:pinephonepro = " kms gbm"
PACKAGECONFIG:append:tenderloin = " kms gbm"
PACKAGECONFIG_DISTRO += "sql-sqlite icu glib accessibility mtdev examples fontconfig xkbcommon"

# We had this enabled in our old gpro/meta-qt5 recipe
PACKAGECONFIG:append = " icu"
# We had this enabled in our old gpro/meta-qt5 recipe
PACKAGECONFIG:append = " glib"
# We had this enabled in our old gpro/meta-qt5 recipe
PACKAGECONFIG:append = " fontconfig"
# We had this enabled in our old gpro/meta-qt5 recipe
PACKAGECONFIG:append = " sql-sqlite"
# No longer added automatically
PACKAGECONFIG[gif] = "-DFEATURE_gif=ON,-DFEATURE_gif=OFF"
PACKAGECONFIG:append = " gif"
# Needed since qtwayland 5.12
PACKAGECONFIG:append:class-target = " xkbcommon"
# Disable loading text in image metadata
PACKAGECONFIG[no-imageio-text-loading] = "-DFEATURE_imageio_text_loading=OFF,-DFEATURE_imageio_text_loading=ON"
PACKAGECONFIG:append = " no-imageio-text-loading"

PACKAGECONFIG[linuxfb] = "-DFEATURE_linuxfb=ON,-DFEATURE_linuxfb=OFF"
PACKAGECONFIG:remove = "linuxfb"

PACKAGECONFIG[ico] = "-DFEATURE_ico=ON,-DFEATURE_ico=OFF"
PACKAGECONFIG:remove = "ico"

PACKAGECONFIG[sessionmanager] = "-DFEATURE_sessionmanager=ON,-DFEATURE_sessionmanager=OFF"
PACKAGECONFIG:remove = "sessionmanager"

PACKAGECONFIG[xlib] = "-DFEATURE_xlib=ON,-DFEATURE_xlib=OFF"
PACKAGECONFIG:remove = "xlib"

PACKAGECONFIG[eglfs-egldevice] = "-DFEATURE_eglfs_egldevice=ON,-DFEATURE_eglfs_egldevice=OFF"
PACKAGECONFIG:remove = "eglfs-egldevice"

PACKAGECONFIG[system-sqlite] = "-DFEATURE_system_sqlite=ON,-DFEATURE_system_sqlite=OFF"
PACKAGECONFIG:append = " system-sqlite"

PACKAGECONFIG[system-pcre2] = "-DFEATURE_system_pcre2=ON,-DFEATURE_system_pcre2=OFF"
PACKAGECONFIG:remove = "system-pcre2"

PACKAGECONFIG:remove = "libinput"

# Do not build tests/ in webos
PACKAGECONFIG:remove = "tests"

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

PATCHTOOL = "git"

# Flags needed for webOS
TARGET_CXXFLAGS:append = " \
    -DQFONTCACHE_MIN_COST=512 \
"

VIRTUAL-RUNTIME_gpu-libs ?= ""
RDEPENDS:${PN} += "${VIRTUAL-RUNTIME_gpu-libs}"
