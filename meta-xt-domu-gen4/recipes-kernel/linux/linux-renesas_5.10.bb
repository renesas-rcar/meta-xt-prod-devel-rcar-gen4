DESCRIPTION = "Linux kernel for Xen DomU on R-Car VC4 based board"
COMPATIBLE_MACHINE = "spider|s4sk|vc4"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "${XT_KERNEL_REPO};nocheckout=1;branch=${XT_KERNEL_BRANCH};protocol=https"
SRCREV = "${AUTOREV}"

LINUX_VERSION ?= "5.10.41"
PV = "${LINUX_VERSION}+git${SRCPV}"

KCONFIG_MODE = "alldefconfig"
SRC_URI += "file://defconfig"
SRC_URI += "file://r8a779f0_ufs.bin"

SRC_URI:append:spider = "\
    file://r8a779f0-spider-domu.dts;subdir=git/arch/${ARCH}/boot/dts/renesas \
"

SRC_URI:append:s4sk = "\
    file://r8a779f0-s4sk-domu.dts;subdir=git/arch/${ARCH}/boot/dts/renesas \
"

SRC_URI:append:vc4 = "\
    file://r8a779f0-vc4-domu.dts;subdir=git/arch/${ARCH}/boot/dts/renesas \
"

KERNEL_DEVICETREE:spider = "\
    renesas/r8a779f0-spider-domu.dtb \
"

KERNEL_DEVICETREE:s4sk = "\
    renesas/r8a779f0-s4sk-domu.dtb \
"
KERNEL_DEVICETREE:vc4 = "\
    renesas/r8a779f0-vc4-domu.dtb \
"

do_download_firmware () {
    install -d ${STAGING_KERNEL_DIR}/firmware
    install -m 755 ${WORKDIR}/r8a779f0_ufs.bin ${STAGING_KERNEL_DIR}/firmware/
}

addtask do_download_firmware after do_configure before do_compile

# Install S4 specific UAPI headers and ufs firmware
do_install_append() {
    install -d ${D}/lib/firmware/
    install -m 0644 ${S}/firmware/r8a779f0_ufs.bin ${D}/lib/firmware/
}
