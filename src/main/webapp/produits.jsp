<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Produits</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #dbeafe, #eff6ff);
            min-height: 100vh;
            padding: 30px;
        }

        .container {
            width: 90%;
            max-width: 1150px;
            margin: auto;
        }

        .title {
            text-align: center;
            color: #1e3a8a;
            margin-bottom: 30px;
            font-size: 34px;
            font-weight: bold;
        }

        .card {
            background: white;
            border-radius: 16px;
            padding: 25px;
            box-shadow: 0 8px 22px rgba(0,0,0,0.12);
            margin-bottom: 30px;
        }

        .card h2 {
            color: #2563eb;
            margin-bottom: 20px;
            font-size: 24px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #374151;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #cbd5e1;
            border-radius: 10px;
            outline: none;
            font-size: 15px;
        }

        .form-group input:focus {
            border-color: #2563eb;
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
        }

        .btn {
            color: white;
            border: none;
            padding: 10px 18px;
            border-radius: 10px;
            font-size: 14px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: 0.3s;
        }

        .btn-add {
            background: #2563eb;
        }

        .btn-add:hover {
            background: #1d4ed8;
        }

        .btn-edit {
            background: #f59e0b;
        }

        .btn-edit:hover {
            background: #d97706;
        }

        .btn-delete {
            background: #dc2626;
        }

        .btn-delete:hover {
            background: #b91c1c;
        }

        .btn-cancel {
            background: #6b7280;
            margin-left: 10px;
        }

        .btn-cancel:hover {
            background: #4b5563;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            overflow: hidden;
            border-radius: 12px;
        }

        thead {
            background: #2563eb;
            color: white;
        }

        th, td {
            padding: 14px;
            text-align: center;
            border-bottom: 1px solid #e5e7eb;
        }

        tbody tr:nth-child(even) {
            background: #f8fafc;
        }

        tbody tr:hover {
            background: #dbeafe;
            transition: 0.3s;
        }

        .empty {
            text-align: center;
            color: #6b7280;
            padding: 20px;
            font-style: italic;
        }

        .actions a {
            margin: 0 4px;
        }

        .footer {
            text-align: center;
            margin-top: 20px;
            color: #6b7280;
            font-size: 14px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="title">Gestion des Produits</h1>

    <div class="card">
        <c:choose>
            <c:when test="${not empty produitToEdit}">
                <h2>Modifier un Produit</h2>
                <form action="produits" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="${produitToEdit.id}">

                    <div class="form-group">
                        <label for="nom">Nom du produit</label>
                        <input type="text" id="nom" name="nom" value="${produitToEdit.nom}" required>
                    </div>

                    <div class="form-group">
                        <label for="prix">Prix</label>
                        <input type="number" id="prix" name="prix" step="0.01" value="${produitToEdit.prix}" required>
                    </div>

                    <div class="form-group">
                        <label for="quantite">Quantité</label>
                        <input type="number" id="quantite" name="quantite" value="${produitToEdit.quantite}" required>
                    </div>

                    <button type="submit" class="btn btn-edit">Mettre à jour</button>
                    <a href="produits" class="btn btn-cancel">Annuler</a>
                </form>
            </c:when>

            <c:otherwise>
                <h2>Ajouter un Produit</h2>
                <form action="produits" method="post">
                    <input type="hidden" name="action" value="add">

                    <div class="form-group">
                        <label for="nom">Nom du produit</label>
                        <input type="text" id="nom" name="nom" placeholder="Entrer le nom du produit" required>
                    </div>

                    <div class="form-group">
                        <label for="prix">Prix</label>
                        <input type="number" id="prix" name="prix" step="0.01" placeholder="Entrer le prix" required>
                    </div>

                    <div class="form-group">
                        <label for="quantite">Quantité</label>
                        <input type="number" id="quantite" name="quantite" placeholder="Entrer la quantité" required>
                    </div>

                    <button type="submit" class="btn btn-add">Ajouter</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="card">
        <h2>Liste des Produits</h2>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prix</th>
                    <th>Quantité</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty listeProduits}">
                        <c:forEach var="p" items="${listeProduits}">
                            <tr>
                                <td>${p.id}</td>
                                <td>${p.nom}</td>
                                <td>${p.prix}</td>
                                <td>${p.quantite}</td>
                                <td class="actions">
                                    <a href="produits?action=edit&id=${p.id}" class="btn btn-edit">Modifier</a>
                                    <a href="produits?action=delete&id=${p.id}"
                                       class="btn btn-delete"
                                       onclick="return confirm('Voulez-vous vraiment supprimer ce produit ?');">
                                       Supprimer
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="5" class="empty">Aucun produit disponible</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>

    <div class="footer">
        Mini projet MVC2 + Hibernate
    </div>
</div>

</body>
</html>