
Release Notes: btl2capEcho MIDlet                
===============================================================

These notes are only intended as a brief guide for building and running
the example.  A more detailed description of the design of this MIDlet
example has been provided as a separate design document.


1. Build environment
--------------------

'JBuilder' with 'MobileSet' plus 'Nokia Developers Suite
for J2ME' were used to build and run the MIDlet on a Windows test
environment.  Installation, configuration and use of these tools is beyond
the scope of the release notes.

A build.xml file is also included to build the MIDlet using ant
It uses the MIDP tasks included in the Antenna project. It can be found
at http://antenna.sourceforge.net. Copy the antenna-bin.jar file to
the ANT_HOME/lib directory
If necessary to customize some properties build.properties can be used


2. Building and running the example using JBuilder
--------------------------------------------------

Step 1. Open the BtsppEcho.jpx project

        This project contains all the .java source files contained
        in the subdirectory '.\src\...'

        Notes:
          - The 'Project' / 'Project Properties' windows can be used
            to set project properties related to building and running a MIDlet.

          - (The 'Tools' / 'Nokia Developer's Suite for J2ME(TM)' /
             'Create Application Descriptor' can be used to create a .jad and
             .jar files. These have already been created as a convienence.)

Step 2. Building the MIDlet

        These JBuilder mechanisms were used to build the MIDlet classfiles:
           'Project' / 'Make Project "btl2capEcho.jpx"'
           'Project' / 'Rebuild Project "btl2capEcho.jpx"'

        You should see a message like: 'Build succeeded. Build took 4 seconds.'

        Once the project has been built at least once as in Step 1,
        one can use:
           'Tools' / 'Nokia Developer's Suite for J2ME' / 'Recreate'
        to recreate the jar file.

Step 3. Running the MIDlet

        Use this mechanism to run the example:
           'Run' / 'Run Project'

        The 'Project' / 'Project Properties' / 'Run' / 'MIDlet' tab
        may be used to run the example using via the .jad and .jar files.
        
        For a Bluetooth piconet, start running the 'Bluetooth slaves' first
        (i.e. the 'application-level clients' in this example) and then start the
        'Bluetooth master' (i.e. the 'application-level server' in this example)
        and initiate the Bluetooth connections from the master to the slaves.

Step 4. Preparing the MIDlet for OTA download

        One may wish to perform various optional steps at this point,
        such as obfuscation, optimized compiling, jar file compression, etc.
        These are beyond the scope of these release notes.

        Because a variety of methods could be used to create the final .jad
        and .jar files used for OTA downloading of the MIDlet:
          - I recommend checking that the attributes in the .jad and
            .jar manifest files are correct, and that they match EXACTLY when
            present in both. One should think about the reasons why some
            arbitrary actual MIDP device might refuse to run a MIDlet
            (JAR size mismatches, attribute mismatches, etc.)
          - The 'check.sh' script gives a model of the types of things one
            might want to double-check (e.g. attribute values should match,
            MIDlet-Jar-Size should match the actual size, MIDlet-Jar-URL
            should be correct, etc.) in the process of preparing .jad and
            .jar files for use in OTA downloading. It is a guide and may
            not be an exhaustive list.
          - It is worth emphasizing that the mandatory parameters of the
            .jad and .jar manifest files should be present, should be correct,
            and should match for those attributes which are present in both.
            The MIDP specification covers the mandatory .jad and .jar
            manifest attributes.


Consult the relevant documentation of your build tools, for any
other questions or issues that may arise.


3. Building and running the example using ant
---------------------------------------------
Having ant and antenna installed the MIDlet can be built using the
'ant' or ant 'build' command.
For a clean build use the
'ant cleanbuild' command


4. Other notes
--------------
This MIDlet does Class of Device (COD) filtering after 'device
inquiry' and before 'service discovery'. This is essential in a real
MIDlet, so the MIDlet don't search to see if a game service is
running on any arbitrary nearby Bluetooth device (e.g. headset, etc.),
but just one the ones where the service is most likely to be found.
See the related code for the COD filtering in method
'deviceDiscovered' of class 'example.btl2capecho.ServiceDiscoveryList'.

However the 'Series 60 MIDP Concept Emulator Beta 0.3.1' doesn't
seem to return values from methods DeviceClass.getMajorDeviceClass()
and DeviceClass.getMinorDeviceClass() that one might
expect from a real mobile phone. That means the COD filtering
code fails when using that beta version of the emulator. As a
workaround if you use that emulator, you can set the parameter
"isEmulator = true" in method 'deviceDiscovered' of class
'example.btl2capecho.ServiceDiscoveryList' by modifying the
Java sourcecode. If you do this, you should remember to set
"isEmulator = false" before compiling the MIDlet for download
onto real a MIDP phone.


