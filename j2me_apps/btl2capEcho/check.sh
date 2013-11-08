#!/bin/sh

# Copyright 2002 Nokia Corporation. 
# 
# THIS SOURCE CODE IS PROVIDED 'AS IS', WITH NO WARRANTIES WHATSOEVER, 
# EXPRESS OR IMPLIED, INCLUDING ANY WARRANTY OF MERCHANTABILITY, FITNESS 
# FOR ANY PARTICULAR PURPOSE, OR ARISING FROM A COURSE OF DEALING, USAGE 
# OR TRADE PRACTICE, RELATING TO THE SOURCE CODE OR ANY WARRANTY OTHERWISE 
# ARISING OUT OF ANY PROPOSAL, SPECIFICATION, OR SAMPLE AND WITH NO 
# OBLIGATION OF NOKIA TO PROVIDE THE LICENSEE WITH ANY MAINTENANCE OR 
# SUPPORT. FURTHERMORE, NOKIA MAKES NO WARRANTY THAT EXERCISE OF THE 
# RIGHTS GRANTED HEREUNDER DOES NOT INFRINGE OR MAY NOT CAUSE INFRINGEMENT 
# OF ANY PATENT OR OTHER INTELLECTUAL PROPERTY RIGHTS OWNED OR CONTROLLED 
# BY THIRD PARTIES 
# 
# Furthermore, information provided in this source code is preliminary, 
# and may be changed substantially prior to final release. Nokia Corporation 
# retains the right to make changes to this source code at 
# any time, without notice. This source code is provided for informational 
# purposes only. 
# 
# Nokia and Nokia Connecting People are registered trademarks of Nokia
# Corporation.
# Java and all Java-based marks are trademarks or registered trademarks of
# Sun Microsystems, Inc.
# Other product and company names mentioned herein may be trademarks or
# trade names of their respective owners.
# 
# A non-exclusive, non-transferable, worldwide, limited license is hereby 
# granted to the Licensee to download, print, reproduce and modify the 
# source code. The licensee has the right to market, sell, distribute and 
# make available the source code in original or modified form only when 
# incorporated into the programs developed by the Licensee. No other 
# license, express or implied, by estoppel or otherwise, to any other 
# intellectual property rights is granted herein.


#
# NAME
#   check.sh
#
# DESCRIPTION
#   A simple shell script to simplify visual sanity checking that the
#   .jad and .jar manifest of a MIDlet suite have correct
#   and matching attributes. The script doesn't check anything
#   itself, its output must be scanned by a real person.
#
#   The shell script is copied to the directory where the .jad
#   and .jar files reside, and run from that directory. It creates
#   and removes a temporary directory called "./tmpCheck".
#


# A temporary directory to work in.
rm -rf ./tmpCheck
mkdir ./tmpCheck
cp *.jad *.jar ./tmpCheck
cd ./tmpCheck
jar xf *.jar

echo ""
echo "-- 1. Check mandatory manifest-only attributes --"
grep Manifest META-INF/MANIFEST.MF
grep MicroEdition META-INF/MANIFEST.MF
echo ""


echo ""
echo "-- 2. Check for trailing spaces in .jad --"
grep ".* $" *.jad
echo ""


echo ""
echo "-- 3. Check for mismatches between mandatory .jad and manifest attrs --"
echo "MIDlet-Name: "
echo "jad:" `grep MIDlet-Name *.jad`
echo "jar:" `grep MIDlet-Name META-INF/MANIFEST.MF`
echo "MIDlet-Version: "
echo "jar:" `grep MIDlet-Version META-INF/MANIFEST.MF`
echo "jad:" `grep MIDlet-Version *.jad`
echo "MIDlet-Vendor: "
echo "jar:" `grep MIDlet-Vendor META-INF/MANIFEST.MF`
echo "jad:" `grep MIDlet-Vendor *.jad`
# MIDlet-<N> should be manifest-only ?
echo "MIDlet-<N>"
echo "jar:" `grep MIDlet-[0-9] META-INF/MANIFEST.MF`
echo "jad:" `grep MIDlet-[0-9] *.jad`


#
# Notes:
# - Setting MIDlet-Icon probably makes more sense in the manifest than in
#   the .jad ? (Otherwise the .jad references something it doesn't yet have.)
# - Setting MIDlet-Data, MIDlet-Description and MIDlet-Info make more
#   sense in the .jad than in the manifest ?
#

echo ""
echo "-- 4. Check optional .jad and manifest attributes --"
echo "MIDlet-Icon: "
echo "jad:" `grep MIDlet-Icon *.jad`
echo "jar:" `grep MIDlet-Icon META-INF/MANIFEST.MF`
echo "MIDlet-Description:"
echo "jad:" `grep MIDlet-Description *.jad`
echo "jar:" `grep MIDlet-Description META-INF/MANIFEST.MF`
echo "MIDlet-Info:"
echo "jad:" `grep MIDlet-Info *.jad`
echo "jar:" `grep MIDlet-Info META-INF/MANIFEST.MF`
echo "MIDlet-Data:"
echo "jad:" `grep MIDlet-Data *.jad`
echo "jar:" `grep MIDlet-Data META-INF/MANIFEST.MF`
echo ""


echo ""
echo "-- 5. Check the actual jar file size matches the .jad --"
echo "jad:" `grep MIDlet-Jar-Size *.jad`
ls -l *.jar


# Cleanup: remove the temporary directory.
cd ..
rm -rf ./tmpCheck
