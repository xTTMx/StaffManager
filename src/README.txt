###################################
#            FOR HELP             #
###################################

Please go to https://github.com/SkyBirdSoar/StaffManager
or http://dev.bukkit.org/bukkit-plugins/staffmanager

###################################
#           CREDITS               #
###################################
Developer: SkyBirdSoar
Special Thanks: Bryansheckler
                xTTMx
                SpiritFusionHD (Thing2)

###################################
#          CHANGELOG              #
###################################
From 1.0.3 to 1.1.0
-------
Alpha Build!
NEW!
 * /sm test was merged into /sm apply
 * Application format now configurable
PLANNED:
 * Ability to read applications in-game
TEDIOUS METHOD OF VIEWING APPLICATIONS:
 * Perform /sm applicants in-game
 * Pick a applicant EX. Bryansheckler
 * Go to: /plugins/StaffManager/players/Bryansheckler.yml
 * Under applications will be the fields he entered.
 * application.desc.lines is the number of lines he used for describing his self!
 * application.desc.1 is the first line,
 * application.desc.2 is the second line and so on and so forth.
 * application.rank.current is his current rank (What he told to StaffManager) (StaffManager does not guarantee this information is correct)
 * application.rank.apply is the rank he is applying for.
 * The rest should be easy to identify.
 * To accept his application, do '/sma app Bryansheckler accept [Rank]'
 * To deny his application, do '/sma app Bryansheckler deny'
HOW TO CONFIGURE WHAT STAFFMANAGER ASKS FOR WHEN PLAYERS APPLY:
 * Go to /plugins/StaffManager/application.yml
 * To set a required field, set true
 * To prevent StaffManager from asking the field, set false
 * PROMPT_1 gives a brief introduction to the player. This is required by default and is not configurable.
 * PROMPT_2 asks for the IGN of the player (Could be used as a player recommendation)
 * PROMPT_3 asks for AGE
 * PROMPT_4 asks for GENDER
 * PROMPT_5 asks for SKYPE (Note: You could tell players to use this to indicate their preferred communication method if they do not use skype EX: [Email] chicken@chicken.net)
 * PROMPT_6 asks for CURRENT RANK (StaffManager does not check the ranks of players. The reliability of this field depends on the integrity of the player)
 * PROMPT_7 asks for THE RANK THE PLAYER IS APPLYING FOR (StaffManager does not check if the rank exists)
 * PROMPT_8 asks for A DESCRIPTION ABOUT THE PLAYER
 * PROMPT_9 allows or disallows multiple lines for description
 * PROMPT_10 asks for STAFF EXPERIENCE (StaffManager does not ensure the reliability of this field too)
