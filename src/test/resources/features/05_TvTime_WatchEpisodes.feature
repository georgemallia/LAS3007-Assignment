
Feature: Watch Episode

Scenario: Watch an Episode
Given that the user is logs in
And the user adds a show to his watchlist
And he opens the next episode to watch
When he marks the episode as watched
Then the watched button text is changed to watched
And the show will be listed under watch next tab

Scenario: UnWatch an Episode
Given that the user is logs in
And the user opens his watchlist
And he opens the next episode to watch
When he selects previous episode
And he marks the episode as unwatched
Then the watched button text is changed to unwatched

Scenario: Watch entire season
Given that the user is logs in
And the user opens his watchlist
And he opens a show
When he clicks the seen button near the season name
Then the whole season should be marked as watched

@TO_RUN
Scenario: Unwatch entire season
Given that the user is logs in
And the user opens his watchlist
And he opens a show
When he selects the previous season
And he clicks the season unwatch button
Then the whole season should be marked as unwatched
