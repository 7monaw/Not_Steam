# NotSteam: Database Project

![Database Systems](https://img.shields.io/badge/domain-database_systems-blue) ![Technology](https://img.shields.io/badge/DBMS-IBM_DB2-orange) ![Java](https://img.shields.io/badge/java-JDBC-blue)

## Purpose
NotSteam is a digital distribution platform that primarily serves as a store for purchasing/installing video games for computers. It is also a social network platform where people can interact with others through in-game chats, community forums and user reviews. The main audience of NotSteam are for people that wish to have a very simple and direct way to buy, install and play online video games on a computer. The game developers and publishers that associate with the platform may also want to be aware of which of their games are doing well, which is possible.

<p align="center">
    <img src="https://github.com/7monaw/Not_Steam/blob/main/ER_Diagram.png" alt
        width="1000" 
        height="667"/>
    <caption>Entity-Relationship Schema</caption>
</p>

## 📂 Complete File Listing

### Core Project Files
- `README.md`    - This documentation file
- `ui/`          - Contains the Java source code (.java) and compiled bytecode (.class) for the user interface.
- `DummyTables/` - Contains code for creating dummy relational tables
- `.vscode`      - VSCode file (can be ignored)

## 🚀 Quick Start

1. Create dummy tables:
```bash
cd DummyTables
chmod +x runall.sh
./runall.sh
```

2. Run UI:
```bash
cd UI
java simpleJDBC.class
```
