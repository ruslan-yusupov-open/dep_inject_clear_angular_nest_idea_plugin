# Inject and clear Angular/Nest dependencies Intellij Plugin

## Description
<!-- Plugin description -->
![gif with demo](injectAndClearAngularPlugin.gif)

This plugin provides two actions -
   1) converts selected class to angular dependency in the constructor
   2) remove unused dependencies from the constructor
<!-- Plugin description end -->

## Prerequisites
* you need Intellij IDEA to build that
* Any Intellij editor starting from 2019.3.1 to use

## How use ready distribution
Video manual (Russian)
https://www.youtube.com/watch?v=py2p0NKX4bI

1) Download the zip file from the build/distribution folder
2) Go to File/Plugins, click the gear icon, press "Install Plugin from Disk"
3) Default hotkeys are Ctrl+; and Ctrl+', you may reassign
4) Also accessible from Code/Angular&Nest Dependency Injections menu
5) To add a dependency, type an Injectable Class, select it and press Inject Dependency
6) To remove unused dependencies press Clear Unused Dependencies
7) After these operation you may need reformat code/optimize imports. 
(In my system it's Ctrl+L and Ctrl+O)

## TODO
The plugin is very simple, I hope JetBrains or the community reimplement this in more smart way.
