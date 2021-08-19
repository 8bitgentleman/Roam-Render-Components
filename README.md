
<h3 align="center">Roam/Render Components</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]()
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
  - [Count Child Blocks](#count)
  - [WIP - Roam to Orbit](#orbit)
  - [WIP - Stopwatch](#stopwatch)

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
  Look for and download the latest JSON Release then import into Roam <a href='https://github.com/8bitgentleman/Roam-Render-Components/releases/'>here </a>

  <img src="https://github.com/8bitgentleman/Roam-Render-Components/raw/main/TODO%20Progress%20Bar/image.gif" width="400"></img>

- ### Count Block Children <a name = "count"></a>
  Counts the number of children a block has.
  Look for and download the latest JSON Release then import into Roam <a href='https://github.com/8bitgentleman/Roam-Render-Components/releases/'>here </a>

  <img src="https://github.com/8bitgentleman/Roam-Render-Components/raw/main/TODO%20Progress%20Bar/image.gif" width="400"></img>

- ### WIP Roam to Orbit <a name = "orbit"></a>
  THIS IS A WORK IN PROGRESS AND NOT READY FOR GENERAL USAGE
  
  This is a component that will integrate with <a href='https://github.com/andymatuschak/orbit'>Andy Matuschak's</a> new open-source experimental spaced repetition  platform <a href='https://github.com/andymatuschak/orbit'>Orbit.</a>
  You will pass a page name string into the component and it will load all prompts and answers that reference that page. You can find the code and optional CSS <a href="https://github.com/8bitgentleman/Roam-Render-Components/raw/main/Roam%20to%20Orbit">here</a>.

- ### WIP Roam Stopwatch <a name = "stopwatch"></a>
  THIS IS A WORK IN PROGRESS AND NOT READY FOR GENERAL USAGE
  
  This is a component that acts as a stopwatch, counting up instead of counting down (like POMO). When clicking the button the current lap length will be added as a child block and the lap will reset. To set a custom title pass in a string in quotes to the componenet. See the code and optional CSS <a href="https://github.com/8bitgentleman/Roam-Render-Components/raw/main/Roam%20Stopwatch">here</a>.
  
## ⛏️ Built Using <a name = "built_using"></a>
- [roam/render](https://roamresearch.com/#/app/developer-documentation/page/7l31uEMqA)
- [Clojurescript](https://clojurescript.org/)

## ✍️ Authors <a name = "authors"></a>
- [Matt Vogel](https://github.com/8bitgentleman) - Idea & Initial work
