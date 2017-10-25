This is Mycroft Core for Android

Currently, it uses a simple text interface to test its interaction with the parser and skills. Eventually this will be replaced with a bindable Pocketsphinx service.

It is designed to query for all packages with an "action.intent.android.PARSER_IDENTIFY" so that multiple parse engines can be designed, depending on the needs of a skill and wants of a designer.

As time goes on new custom intents can be added, so that other components such as pocketsphinx or the TTS modules can be replaced.

Eventually I am to individualize the Assistant to each user, and incorporate Machine Learning packages, but for now I am trying to replicate Mycrofts core functionality.