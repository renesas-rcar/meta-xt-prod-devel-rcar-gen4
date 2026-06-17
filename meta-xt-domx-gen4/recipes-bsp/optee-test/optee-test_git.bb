SUMMARY = "OP-TEE sanity testsuite"
HOMEPAGE = "https://github.com/OP-TEE/optee_test"

LICENSE = "BSD-2-Clause & GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=a8fa504109e4cd7ea575bc49ea4be560"

DEPENDS = "optee-client optee-os python3-cryptography-native openssl"

inherit python3native
inherit deploy

PV = "4.3.0+git${SRCPV}"

SRC_URI = "git://github.com/OP-TEE/optee_test.git;branch=master;protocol=https \
          "
S = "${WORKDIR}/git"

SRCREV = "9d4c4fb9638fb533211037016b6da12fbbcc4bb6"

OPTEE_CLIENT_EXPORT = "${STAGING_DIR_HOST}${prefix}"
TEEC_EXPORT         = "${STAGING_DIR_HOST}${prefix}"
TA_DEV_KIT_DIR      = "${STAGING_INCDIR}/optee/export-user_ta"

EXTRA_OEMAKE = " TA_DEV_KIT_DIR=${TA_DEV_KIT_DIR} \
                 OPTEE_CLIENT_EXPORT=${OPTEE_CLIENT_EXPORT} \
                 TEEC_EXPORT=${TEEC_EXPORT} \
                 CROSS_COMPILE_HOST=${TARGET_PREFIX} \
                 CROSS_COMPILE_TA=${TARGET_PREFIX} \
                 V=1 \
                 O=${B} \
               "

do_compile:prepend() {
    export CRYPTOGRAPHY_OPENSSL_NO_LEGACY=1
}

do_compile() {
    cd ${S}
    # Top level makefile doesn't seem to handle parallel make gracefully
    oe_runmake xtest
    oe_runmake ta
    oe_runmake test_plugin
}

do_install () {
    install -D -p -m0755 ${B}/xtest/xtest ${D}${bindir}/xtest

    # install path should match the value set in optee-client/tee-supplicant
    # default TEEC_LOAD_PATH is /lib
    mkdir -p ${D}${nonarch_base_libdir}/optee_armtz/
    install -D -p -m0444 ${B}/ta/*/*.ta ${D}${nonarch_base_libdir}/optee_armtz/

    # install plugin(s)
    mkdir -p ${D}${libdir}/tee-supplicant/plugins
    install -D -p -m0444 ${B}/supp_plugin/*.plugin ${D}${libdir}/tee-supplicant/plugins/
}

do_deploy () {
    install -d ${DEPLOYDIR}/${MLPREFIX}optee/ta
    install -m 644 ${B}/ta/*/*.elf ${DEPLOYDIR}/${MLPREFIX}optee/ta
}

addtask deploy before do_build after do_install

FILES:${PN} += "\
                ${nonarch_base_libdir}/optee_armtz/ \
                ${libdir}/tee-supplicant/plugins/ \
                ${bindir}/ \
"

# Imports machine specific configs from staging to build
PACKAGE_ARCH = "${MACHINE_ARCH}"

