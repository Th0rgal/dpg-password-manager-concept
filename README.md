# Deterministic Password Generator

## Concept

A traditional password manager can generate passwords and store them encrypted. DPG does not require storage and works offline. Indeed the algorithm only requires a master password and some information (your login and the name of the website) that it will use to generate a password. This is deterministic, it means that if you change any information (like the website name or even your master password) the generated password will be completely different, but if you don't it will be exactly the same. So you only have to remember the master password to regenerate your password for any website.

## Features

The core of DPG is already developed and allows you to generate passwords depending on the website, your login, your master password obviously but also additional information (useful in order to generate a completely different password without having to change other information if the website got hacked). You can also set the size of the generated password as well as the characters used (because some websites only support numbers while others require special characters).
All these informations (except the master password)  take time to write and do not provide security because they are public, that's why I'm working on a system to save and load them in json format.

## What does DPG bring?

DPG is not the first password manager generating the password every time to avoid storing it (I was told about lesspass.com which seems to be a really interesting project) but in general these programs work using a key derivation function. This is not a bad idea but I think it is not necessary, as using complex functions does not bring more security. I tried to make the DPG algorithm as simple as possible so that everyone can understand how it works and contribute to improve it.

## How does it work?

DPG requires two things to generate the password:

- ### The seed
  The seed is a text variable made by adding the different login information.

   > #### Example: 
   > Your want to set your password on "*github.com*" using your login "*Th0rgal*" and your secret master password is "*iL0veP1nK*". This is the first time that you set your password so you don't need additional information.
   >
   > - **The format is**: *website url* + *login* + *additional salt* + *main password*
   >
   > - **So your seed is**: github.comTh0rgaliL0veP1nK

- ### The password settings

  Depending on the sites you use or your own needs you do not necessarily need the same character set and the same password size. For example, a pin code only uses numbers, while some sites allow you to use letters as well. Your passwords settings are basically composed of two things:

  - **The password size**: for example 15 characters
  - **The character set**: for example abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789

DPG will then use [SHA3-256](https://wikipedia.org/wiki/SHA-3) to generate a big number from the seed. It will next use this number to initialize a random number generator (for now a simple linear congruential generator but I am open to other proposals). This random number generator will randomly generate as much char from the character set as needed to reach password size defined in the password settings.

> ### Recap
>
> 1. Get master password, login informations and password settings from the user
>
>    Example: 
>
>    - website url: "github.com"
>    - login: "Th0rgal"
>    - additional salt: ""
>    - main password: "iL0veP1nK"
>    - password size: 15
>
> 2. Concatenate login informations with the master password
>
>    Example:
>
>    - github.comTh0rgaliL0veP1nK
>
> 3. Generate the seed by hashing it using SHA3-256
>    Example:
>
>    - 47171758159645127635691229880386423006570814195850320683677173456882022719273
>
> 4. Initialize the random number generator with this seed, then generate random letters according to password settings using the random number generator
>    Example:
>
>    - 42ou8EJNK4yRavD

