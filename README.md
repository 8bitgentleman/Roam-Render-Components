
<h3 align="center">Roam/Render Components</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]()
  [![GitHub Issues](https://img.shields.io/github/issues/8bitgentleman/spaced-repetition.svg)](https://github.com/8bitgentleman/Roam-Render-Components/issues)
  [![GitHub Pull Requests](https://img.shields.io/github/issues-pr/8bitgentleman/spaced-repetition.svg)](https://github.com/8bitgentleman/Roam-Render-Components/pulls)
  [![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---

<p align="center"> A collection of custom components for <a href="https://roamresearch.com">Roam Research</a>, writing in roam/render with clojurescript.
    <br>
</p>

## 📝 Table of Contents
- [Installing](#getting_started)
- [Usage](#usage)
- [Components](#components)
  - [TODO Progress Bar](#progress)
  - [WIP - Roam to Orbit](#orbit)
- [Authors](#authors)

## 🏁 Installing <a name = "getting_started"></a>

### Prerequisites
Make sure "Custom Code" is enabled within your settings.


### Importing
To install, import the .JSON file for the desired component into Roam.


## 🎈 Usage <a name="usage"></a>
To add the progress bar you can trigger it with the included Roam template. From within Roam type

```;;COMPONENT_NAME_HERE```

and select the item

## 🤖 Components <a name = "components"></a>


- ### TODO Progress Bar <a name = "progress"></a>
  Download the latest JSON for importing into Roam <a href='https://github.com/8bitgentleman/Roam-Render-Components/releases/'>here </a>

  <img src="https://github.com/8bitgentleman/Roam-Render-Components/raw/main/TODO%20Progress%20Bar/image.gif" width="400"></img>

- ### WIP Roam to Orbit <a name = "orbit"></a>
  THIS IS A WORK IN PROGRESS AND NOT READY FOR GENERAL USAGE
  
  This is a component that will integrate with <a href='https://github.com/andymatuschak/orbit'>Andy Matuschak's</a> new open-source experimental spaced repetition  platform <a href='https://github.com/andymatuschak/orbit'>Orbit.</a>
  You will pass a page name string into the component and it will load all prompts and answers that reference that page

## ⛏️ Built Using <a name = "built_using"></a>
- [roam/render](https://roamresearch.com/#/app/developer-documentation/page/7l31uEMqA)
- [Clojurescript](https://clojurescript.org/)

## ✍️ Authors <a name = "authors"></a>
- [Matt Vogel](https://github.com/8bitgentleman) - Idea & Initial work
