[![Built with Spacemacs](https://cdn.rawgit.com/syl20bnr/spacemacs/442d025779da2f62fc86c2082703697714db6514/assets/spacemacs-badge.svg)](http://spacemacs.org)

# Flashcards



## Installation && Usage
- clone this repo
- navigate into folder
- run `lein run`
- enjoy

## Play with custom flashcards
- navigate to `resources`
- create a new csv in the format of:
  - `question,answer,category`
- navigate to runner file `flashcards-clojure/src/flashcards/runner.clj`
  - change `line 7` filename to match the file you created
  - et voila
  - in terminal do `lein run`


## License

Copyright © 2019 Baal Software

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
