ALT Linux CLI Utility - Installation and Usage Guide

This document provides step-by-step instructions for installing and using the AltLinuxCLI utility on ALT Linux Workstation K 10.1. The utility compares packages from two different branches of ALT Linux and generates a comparison report.
Requirements:

ALT Linux Workstation K 10.1
Java Runtime Environment (JRE) 11 or higher
Internet connection (for accessing ALT Linux package API)

1. Download and Install Java (if not installed)

First, ensure that Java is installed on your ALT Linux system.

1) Open a terminal.
2) Check if Java is already installed:
3) java -version

If Java is installed, you should see the version information. If not, install Java:

Install Java (OpenJDK):

    sudo apt-get update
    sudo apt-get install openjdk-11-jre

2. Download or Build the AltLinuxCLI JAR File

If you have the pre-built JAR file, skip to the next section. If you need to build the JAR file from source, follow these steps:
1) Clone the project repository:

    git clone https://github.com/ComunisticheskiuBOT/Exe.git
    cd Exe

Build the JAR file: Ensure that Maven is installed on your system. If not, install it with:

  sudo apt-get install maven

Once Maven is installed, you can build the JAR file using:

  mvn clean package

After the build is complete, the JAR file will be located in the target directory:

  target/altlinuxcli-1.0.jar

3. Install the Utility in the System
Move the JAR file to a directory according to the FHS (Filesystem Hierarchy Standard). A common location for user-installed binaries is /usr/local/bin.
Move the JAR file:

  sudo mv target/altlinuxcli-1.0.jar /usr/local/bin/altlinuxcli.jar

Optionally, you can create a shell script to make running the tool easier:

  sudo nano /usr/local/bin/altlinuxcli

Add the following content to the script:

#!/bin/bash
  java -jar /usr/local/bin/altlinuxcli.jar "$@"

Save and close the file (CTRL+X, then Y).

Make the script executable:


  sudo chmod +x /usr/local/bin/altlinuxcli

4. Usage

Once the JAR file is installed and the script is created, you can run the utility directly from the terminal.
Syntax:

  altlinuxcli <branch1> <branch2>

Example:

To compare the packages from two branches of ALT Linux, for example, sisyphus and p10:


  altlinuxcli sisyphus p10
