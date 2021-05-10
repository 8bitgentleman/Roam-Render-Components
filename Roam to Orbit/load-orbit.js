var existing = document.getElementById("orbit-main");
if (!existing) {
  var extension = document.createElement("script");
  extension.src = "https://js.withorbit.com/orbit-web-component.js";
  extension.id = "orbit-main";
  extension.async = true;
  extension.type = "module";
} 