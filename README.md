# Inject and clear Angular/Nest dependencies Intellij Plugin

## Description
This plugin provides two actions -
   1) converts selected class to angular dependency in the constructor
   2) remove unused dependencies from the constructor

## Prerequisites
* you need Intellij IDEA to build that
* Any Intellij editor starting form 2019.2 to use

## How use ready distribution
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
