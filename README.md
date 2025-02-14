# meta-xt-rcar-gen4 #

This repository contains Renesas R-Car Gen4-specific Yocto layers for
Xen Troops distro. Some layers in this repository are product-independent.
They provide common facilities that may be used by any xt-based product
that runs on Renesas Gen4-based platforms.

Those layers *may* be added and used manually, but they were written
with [Moulin](https://moulin.readthedocs.io/en/latest/) build system,
as Moulin-based project files provide correct entries in local.conf

# Status

This is a release of the Xen-based development product for S4 boards
(Spider, S4 Starter Kit, Vehicle Computer 4) to support the R-Car S4
Ethernet Switching Application Note.

This release provides the following features:

 - Xen build compatible with S4 SoC
 - Thin Dom0
 - Driver domain (DomD), which has access to all available hardware
 - Optional generic domain (DomU)
 - Support for OP-TEE in virtualization mode
 - R-Switch virtual port to allow hardware offloaded traffic from DomU
 - R-Switch L2 switching offload (including internal and external ports)
 - R-Switch L3 routing offload (including internal and external ports)
 - Virtualized OP-TEE support
 - PCIe SR-IOV support

The following HW modules were tested and are confirmed to work:

 - Serial console (HSCIF)
 - IPMMUs
 - R-Switch
 - eMMC
 - PCIe with ITS (but there is a running issue with MSI interrupts
   that sometimes do not work)

# External dependencies

At least IPL 0.5.0 is required for normal operation. Release was
tested with IPL 3.6.0. User is required to flash ARM TF
(bl31-{board}.srec) and OP-TEE (tee-{board}.srec) provided by the build
to ensure that Xen and DomD/DomU will work correctly.

# Documentation (currently in preparation)

- [Application Note](https://www.renesas.com/en/search?keywords=s4)
- [Building][]
- [Virtualization][]

[Building]: ./doc/building.md
[Virtualization]: ./doc/virtualization.md