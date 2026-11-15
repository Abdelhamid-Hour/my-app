# Réponses TP3

## Question 1

Après un échec de connexion, on utilise un `forward` pour conserver les attributs de
la requête, notamment le message d'erreur et le login saisi. Après une réussite, on
utilise un `redirect` vers le tableau de bord afin de créer une nouvelle requête.

## Question 2

Un `forward` après un POST laisse la requête POST dans l'historique du navigateur.
Un rafraîchissement peut donc renvoyer le formulaire et répéter l'opération.
Avec un `redirect`, le navigateur reçoit une nouvelle requête GET. Ce modèle est
appelé PRG : Post/Redirect/Get.

## Question 3

Une valeur saisie comme `<script>alert('XSS')</script>` peut être interprétée comme
du HTML ou du JavaScript si elle est affichée sans échappement. `c:out` échappe les
caractères HTML et affiche la valeur comme du texte. Il faut donc utiliser `c:out`
pour les valeurs provenant de l'utilisateur.

## Question 4

Un filtre centralise une règle commune et évite de répéter le contrôle de session
dans chaque servlet. Il rend également l'application plus facile à maintenir.
Les filtres peuvent aussi servir à définir l'encodage des requêtes, journaliser
les requêtes ou ajouter des en-têtes HTTP.
