# 🎟️ NAK 2025 — Système de Gestion des Accréditations & Contrôle d'Accès

**NAK 2025** est une application desktop robuste développée en **Java (Swing / MVC)** pour automatiser et sécuriser la gestion des demandes d'accréditations, l'attribution des stands, l'impression des badges et le contrôle d'accès lors des **Nuits Atypiques de Koudougou (NAK 2025)** au Burkina Faso.

---

## 🌟 Fonctionnalités Principales

### 🔐 1. Authentification & Rôles Utilisateurs (`AuthController.java`, `LoginView.java`)
* Gestion des accès sécurisés par profil utilisateur (Administrateurs, Organisateurs, Agents de sécurité / Contrôleurs).
* Contrôle des permissions et sessions d'utilisation.

### 📝 2. Gestion des Demandes d'Accréditation & Stands (`DemandeController.java`, `DemandeView.java`)
* Formulaire d'enregistrement des demandes pour exposants, festivaliers, artistes, journalistes et VIP.
* Validation, rejet ou mise en attente des demandes par le comité d'organisation.
* Suivi des quotas de stands et gestion des catégories d'accès.

### 🚪 3. Module de Contrôle d'Accès en Temps Réel (`ControleAccesView.java`)
* Interface dédiée aux points de contrôle aux entrées du festival.
* Vérification instantanée de la validité des laissez-passer et badges d'accès.
* Prévention des fraudes et doublons d'accès.

### 🖨️ 4. Impression & Génération de Badges (`ImprimerView.java`)
* Module d'impression des badges officiels avec informations d'identification et catégorie d'accès.

### 📊 5. Tableaux de Bord & Statistiques (`OrganisateurDashboard.java`, `DashboardView.java`)
* Suivi en temps réel des statistiques globales : nombre d'accréditations validées, répartition par catégorie, flux aux entrées.

### 🔍 6. Moteur de Recherche Multicritère (`RechercheView.java`)
* Recherche rapide des participants et exposants par nom, catégorie ou numéro de badge.

---

## 🛠️ Stack Technique

* **Langage :** Java SE (JDK 17+)
* **Interface Graphique :** Java Swing / AWT (Design responsive pour application de bureau)
* **Architecture :** Modèle-Vue-Contrôleur (MVC) modulaire et découplé
* **Persistance & Base de Données :** JDBC, Oracle Database / MySQL
* **Environnement de Développement :** Eclipse IDE / VS Code / IntelliJ IDEA

---

## 📁 Structure du Code Source (`src/`)

```
src/
├── controller/            # Logique métier et contrôleurs
│   ├── AuthController.java        # Gestion des connexions et droits
│   └── DemandeController.java     # Traitement des demandes d'accréditation
├── util/                  # Utilitaires et connecteurs
│   └── DatabaseConnection.java    # Connexion JDBC à la base de données
└── view/                  # Vues et interfaces graphiques Swing
    ├── LoginView.java             # Écran de connexion
    ├── DashboardView.java         # Vue du tableau de bord
    ├── OrganisateurDashboard.java # Espace superviseur organisateur
    ├── DemandeView.java           # Formulaire de demande
    ├── GestionDemandesView.java   # Tableau de validation des demandes
    ├── ControleAccesView.java     # Interface de scan / contrôle d'accès
    ├── ImprimerView.java          # Module d'impression des badges
    └── RechercheView.java         # Moteur de recherche
```

---

## 🚀 Installation & Exécution

### Prérequis
* **JDK 17** ou supérieur installé
* Serveur de base de données (Oracle XE ou MySQL) avec le schéma de table configuré

### 1. Cloner le projet
```bash
git clone https://github.com/Alya351/NAK_Gestion.git
cd NAK_Gestion
```

### 2. Configurer la base de données
Modifiez les paramètres de connexion dans [`src/util/DatabaseConnection.java`](src/util/DatabaseConnection.java) selon votre environnement local :
```java
private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
private static final String USER = "votre_utilisateur";
private static final String PASSWORD = "votre_mot_de_passe";
```

### 3. Compiler et Lancer l'application
Dans votre terminal ou votre IDE (Eclipse / IntelliJ) :
```bash
javac -d bin src/controller/*.java src/util/*.java src/view/*.java
java -cp bin view.LoginView
```

---

## 👩‍💻 Auteur & Équipe

**Kiemde Banyala Latifa Alya**  
*Élève Ingénieure en Systèmes Numériques*  
*ISGE-BF (Institut Supérieur de Génie Électrique du Burkina Faso)*  
*Groupe 09 — Projet Académique NAK 2025*
