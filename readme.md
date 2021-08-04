<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![License][license-shield]][license-url]


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/Exortions/PluginUtils">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">PluginUtils</h3>

  <p align="center">
    PluginUtils is a simple, easy-to-use
            library that saves many Minecraft Spigot developers a lot of time when creating
            plugins. It allows Spigot developers to create a lot of NMS
            (net.minecraft.server) and Spigot things like Tablists, Scoreboards, etc, as well as MySQL and SQLite support,
            a bunch of lines and classes saved, therefore improving the experience while
            developing plugins.
    <br />
    <a href="https://github.com/Exortions/PluginUtils"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/Exortions/PluginUtils">View Demo</a>
    ·
    <a href="https://github.com/Exortions/PluginUtils/issues">Report Bug</a>
    ·
    <a href="https://github.com/Exortions/PluginUtils/issues">Request Feature</a>
  </p>




<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

            PluginUtils is a library that saves many Minecraft Spigot developers 
            a lot of time when creating plugins. It allows Spigot developers to 
            create a lot of NMS (net.minecraft.server) and Spigot things like Tablists,
            Scoreboards, etc, as well as MySQL and SQLite support, a bunch of lines and
            classes saved, therefore improving the experience while developing plugins.


### Built With

* [Spigot API](https://www.spigotmc.org/wiki/spigot-maven/)
* [Net Minecraft Server](https://www.minecraft.net/)
* [Maven](https://maven.apache.org/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

<!-- GETTING STARTED -->
## Installation

Below are instructions to install the latest stable version of PluginUtils.

### Prerequisites
* Java
  ```sh
  Head to:
  https://www.java.com/en/download/
  and download Java Version 8 update <latest update>
  ```

### Installation

Maven:
   ```XML
   <dependency>
        <groupId>com.exortions</groupId>
        <artifactId>ExosPluginUtils</artifactId>
        <version>LATEST VERSION</version>
    </dependency>
   ```

Gradle:
   ```GROOVY
    plugins {
        id 'java-library'
    }
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        implementation 'com.exortions:ExosPluginUtils:LATEST VERSION'
    }
   ```



<!-- USAGE EXAMPLES -->

## Usage

To see usages for all the features in PluginUtils, please refer to the [Documentation](https://example.com).



<!-- ROADMAP -->
## Roadmap
See the [open issues](https://github.com/Exortions/PluginUtils/issues) for a list of proposed features (and known issues).

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the Apache 2.0 License. See `LICENSE` for more information.

<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements

* [Spigot API](https://www.spigotmc.org/wiki/spigot-maven/)
* [Net Minecraft Server](https://www.minecraft.net/)
* [Maven](https://maven.apache.org/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
* [Img Shields](https://shields.io)
* [Readme Template](https://github.com/othneildrew/Best-README-Template)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Exortions/PluginUtils.svg?style=for-the-badge
[contributors-url]: https://github.com/Exortions/PluginUtils/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Exortions/PluginUtils.svg?style=for-the-badge
[forks-url]: https://github.com/Exortions/PluginUtils/network/members
[stars-shield]: https://img.shields.io/github/stars/Exortions/PluginUtils.svg?style=for-the-badge
[stars-url]: https://github.com/Exortions/PluginUtils/stargazers
[issues-shield]: https://img.shields.io/github/issues/Exortions/PluginUtils.svg?style=for-the-badge
[issues-url]: https://github.com/Exortions/PluginUtils/issues
[license-shield]: https://img.shields.io/github/license/Exortions/PluginUtils.svg?style=for-the-badge
[license-url]: https://github.com/Exortions/PluginUtils/blob/master/LICENSE.txt
