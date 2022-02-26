
<h3 align="center">Roam/Render Components</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]()
  [![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---

<p align="center"> A collection of custom components for <a href="https://roamresearch.com">Roam Research</a>, writing in roam/render with clojurescript. 
    <br>
    Interested in Roam JS plugins? Check some of mine <a href='https://github.com/8bitgentleman/roam-custom-theme/tree/master/roam-js'>here </a>

</p>

## 📝 Table of Contents
- [Installing](#getting_started)
- [Usage](#usage)
- [Components](#components)
  - [TODO Progress Bar](#progress)
  - [Count Child Blocks](#count)
  - [Namespace Pages List](#namespace)
  - [Shopping List Sum Prices](#sum)
  - [WIP - Highlight Review](#highlight)
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

  <img src="https://github.com/8bitgentleman/Roam-Render-Components/raw/main/Count%20Block%20Children/image.gif" width="400"></img>

- ### Namespace Page List <a name = "namespace"></a>
  This component will create a dynamic list of all pages within a certain namespace (`roam` for example would include `roam/js`, `roam/css`, etc ). This can be useful to see a dynamic list of all Projects, Books, People, etc. To use simply trigger the included template ( `;;`) and pass your desired namespace into the component as an argument
  Look for and download the latest JSON Release then import into Roam <a href='https://github.com/8bitgentleman/Roam-Render-Components/releases/'>here </a>

  <img src="https://github.com/8bitgentleman/Roam-Render-Components/raw/main/Namespace%20Pages%20List/image.gif" width="400"></img>

- ### Shopping List Sum Prices <a name = "sum"></a>
  Searches all block children for prices in USD (eg. $10) and sums everything up. Look for and download the latest JSON Release then import into Roam  <a href='https://github.com/8bitgentleman/Roam-Render-Components/releases/'>here </a>

  <img src="https://github.com/8bitgentleman/Roam-Render-Components/raw/main/Shopping%20List%20Sum/image.gif" width="400"></img>

- ### WIP Highlight Review <a name = "highlight"></a>
  This is a WIP component which creates a custom UI where highlights/quotes in your graph can be reviewed. For this extension to work highlights must be nested under a specific tag/page/attribute quotes.  It's currently extremely picky about the format and metadata that your pages have. I've included my personal template but any block nested under #quotes should be seen by the component. 
  Look for and download the latest JSON Release then import into Roam    <a href='https://github.com/8bitgentleman/Roam-Render-Components/releases/'>here </a>

  <img src="https://github.com/8bitgentleman/Roam-Render-Components/raw/main/Highlight%20Review/image.gif" width="400"></img>

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
