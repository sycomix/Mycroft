This is Mycroft Core for Android

Currently, it uses a simple text interface to test its interaction with the parser and skills. Eventually this will be replaced with a bindable Pocketsphinx service.

It is designed to query all newly installed packages, and/or the package manager for all packages with an "action.intent.android.PARSER_IDENTIFY" so that multiple parse engines can be designed, depending on the needs of a skill and wants of a designer.

Mycrofts install broadcast listener should register all module install intents, so that if a skill is installed for an intent parser, it can send an explicit intent to that parser to wake it and install the skill

Because Mycroft will need to hold a wakelock, and be an active high priority service, it's notification bar should have an edittext and submit button for users to enter textual input if they are unable to talk

As time goes on new custom intents can be added, so that other components such as pocketsphinx or the TTS modules can be replaced.

Eventually I am to individualize the Assistant to each user, and incorporate Machine Learning packages, but for now I am trying to replicate Mycrofts core functionality.