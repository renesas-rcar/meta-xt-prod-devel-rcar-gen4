FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

RENESAS_BSP_URL = "git://github.com/renesas-rcar/linux-xen.git"
BRANCH = "${XT_KERNEL_BRANCH}"
SRCREV = "${XT_KERNEL_REV}"

SRC_URI:append:spider = "\
    file://r8a779f0-spider-domu.dts;subdir=git/arch/${ARCH}/boot/dts/renesas \
"

SRC_URI:append:s4sk = "\
    file://r8a779f0-s4sk-domu.dts;subdir=git/arch/${ARCH}/boot/dts/renesas \
"

SRC_URI:append = "\
    file://rswitch.cfg \
    file://dmatest.cfg \
    file://ixgbevf.cfg \
"

KERNEL_DEVICETREE:append:spider = " renesas/r8a779f0-spider-domu.dtb"
KERNEL_DEVICETREE:append:s4sk = " renesas/r8a779f0-s4sk-domu.dtb"
