FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RENESAS_BSP_URL = "git://github.com/renesas-rcar/linux-xen.git"
BRANCH = "${XT_KERNEL_BRANCH}"
SRCREV = "${XT_KERNEL_REV}"

SRC_URI_append = "\
    file://r8a779f0-s4sk-domu.dts;subdir=git/arch/${ARCH}/boot/dts/renesas \
    file://rswitch.cfg \
    file://dmatest.cfg \
    file://ixgbevf.cfg \
"

KERNEL_DEVICETREE_append = " renesas/r8a779f0-s4sk-domu.dtb"
