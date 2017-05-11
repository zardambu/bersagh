cat /.ssh/config

Host github-pers
    HostName github.com
    User git
    IdentityFile ~/.ssh/id_rsa_personal
    IdentitiesOnly yes

Host github-rms
    HostName github.com
    User git
    IdentityFile ~/.ssh/id_rsa_work
    IdentitiesOnly yes



- Creates a new ssh key, using the provided email as a label
    + `ssh-keygen -t rsa -b 4096 -C "your_email@example.com"`

- start the ssh-agent in the background
    + `eval "$(ssh-agent -s)"`

- add ssh key to the ssh-agent
    + `ssh-add ~/.ssh/id_rsa_personal`

- Copies the contents of the id_rsa_personal.pub file to your clipboard
    + `pbcopy < ~/.ssh/id_rsa_personal.pub`
