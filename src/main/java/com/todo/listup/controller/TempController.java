package com.todo.listup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

  @GetMapping("/test/cont")
  public String test() {
    return "<!doctype html>"
        + "<html>"
        + "  <head>"
        + "    <meta charset=\"utf-8\">"
        + "    <title>Heart Beat</title>"
        + "  </head>"
        + "  <body>"
        + "    <div id=\"box\">"
        + "      <span class=\"hex-icon-heart\">"
        + "        <svg>"
        + "          <path d=\"M19,1 Q21,0,23,1 L39,10 Q41.5,11,42,14 L42,36 Q41.5,39,39,40 L23,49 Q21,50,19,49 L3,40 Q0.5,39,0,36 L0,14 Q0.5,11,3,10 L19,1\" />"
        + "          <path d=\"M11,17 Q16,14,21,20 Q26,14,31,17 Q35,22,31,27 L21,36 L11,27 Q7,22,11,17\" />"
        + "        </svg>"
        + "      </span>"
        + "    </div>"
        + "    <style>"
        + "      html, body { height: 100%; }"
        + "      body { margin: 0; padding: 0; background: #1a1c24; }"
        + "      #box { position: relative; top: 50%; text-align: center; margin-top: -25px; }"
        + "      #box [class^=\"hex-icon\"] { vertical-align: top; }"
        + "      [class^=\"hex-icon\"] { width: 42px; height: 50px; margin: 0 10px; display: inline-block; transition: all 0.2s cubic-bezier(0.215, 0.610, 0.355, 1.000); -webkit-transition: all 0.2s cubic-bezier(0.215, 0.610, 0.355, 1.000); }"
        + "      [class^=\"hex-icon\"]:hover { transform: scale3d(1.2, 1.2, 1); -webkit-transform: scale3d(1.2, 1.2, 1); transition: all 0.35s cubic-bezier(0.000, 1.270, 0.460, 1.650); -webkit-transition: all 0.35s cubic-bezier(0.000, 1.270, 0.460, 1.650); }"
        + "      [class^=\"hex-icon\"] svg { width: 100%; height: 100%; display: block; }"
        + "      .hex-icon-sun path:first-of-type { fill: #fff; }"
        + "      .hex-icon-sun circle { stroke: #757579; stroke-width: 2px; fill: none; }"
        + "      .hex-icon-sun circle:last-of-type { stroke-width: 2px; stroke-dasharray: 2, 7.4; }"
        + "      .hex-icon-wave path:first-of-type { fill: #219cb5; }"
        + "      .hex-icon-wave circle { stroke: #fff; stroke-width: 2px; fill: none; }"
        + "      .hex-icon-wave mask circle { fill: #fff; stroke: none; }"
        + "      .hex-icon-wave path:last-of-type { fill: #fff; }"
        + "      .hex-icon-heart path:first-of-type { fill: #7b5af7; }"
        + "      .hex-icon-heart path:last-of-type { fill: #fff; transform-origin: 21px 25px; -webkit-transform-origin: 21px 25px; animation: hex-icon-heart-beat 1s linear infinite; }"
        + "      @keyframes hex-icon-heart-beat {"
        + "        0% { transform: scale3d(1, 1, 1); }"
        + "        30% { transform: scale3d(0.75, 0.75, 1); }"
        + "        60% { transform: scale3d(1, 1, 1); }"
        + "      }"
        + "      @-webkit-keyframes hex-icon-heart-beat {"
        + "        0% { -webkit-transform: scale3d(1, 1, 1); }"
        + "        30% { -webkit-transform: scale3d(0.75, 0.75, 1); }"
        + "        60% { -webkit-transform: scale3d(1, 1, 1); }"
        + "      }"
        + "    </style>"
        + "  </body>"
        + "</html>";
  }
}
