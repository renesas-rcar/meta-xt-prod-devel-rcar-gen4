FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

require recipes-kernel/inc/linux-sources.inc

# Ignore in-tree defconfig
KBUILD_DEFCONFIG = ""

SRC_URI:append = "\
    file://r8a779f0-${MACHINE}-domu.dts;subdir=git/arch/${ARCH}/boot/dts/renesas \
    file://rswitch.cfg \
    file://dmatest.cfg \
    file://ixgbevf.cfg \
    file://defconfig \
"

KERNEL_DEVICETREE:append = " renesas/r8a779f0-${MACHINE}-domu.dtb"
