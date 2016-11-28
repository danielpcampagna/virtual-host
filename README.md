# virtual-host

---

## Início

Para adquirir o projeto, siga os passos:

* 1. Obtenha o projeto do github

```
git clone git@github.com:danielpcampagna/virtual-host.git
```

* 2. Instale as dependências, compile e inicie o serviço

```
make install

make setup

make start
```


## Para colaborar com o projeto, siga os passos:

* 1. Obtenha a versão atual estável (`master`)

```
git checkout master
git pull origin master
```

* 2. Crie uma nova `branch` a partir da master

```
git checkout -b feature/nome-da-sua-branch
```

* 3. Após as alterações, o commit e o push, faça uma merge da master para a sua branch e teste.

```
git add .
git commit -m "mensagem-do-commit"
git push origin feature/nome-da-sua-branch
git fetch --all
git merge master
```

* 4. Caso esteja funcionando, faça o merge do seu conteúdo para a master

```
git checkout master
git pull origin master
git merge	feature/nome-da-sua-branch
```

