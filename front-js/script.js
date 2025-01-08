// Variables
let currentInput = '';
let previousInput = '';
let operator = '';
const screen = document.getElementById('screen');
const addPluginBtn = document.getElementById('addPluginBtn');
const operationButtons = document.getElementById('operationButtons');

// Fonction pour afficher l'écran
function updateScreen() {
    screen.value = currentInput;
}

// Ajouter un chiffre à l'entrée courante
document.querySelectorAll('.number').forEach(button => {
    button.addEventListener('click', () => {
        currentInput += button.textContent;
        updateScreen();
    });
});

// Ajouter une opération à l'entrée courante
function addOperationButton(operation) {
    const button = document.createElement('button');
    button.textContent = operation;
    button.classList.add('operation');
    button.dataset.operation = operation;

    // Ajouter l'événement pour effectuer l'opération
    button.addEventListener('click', () => {
        if (currentInput !== '') {
            previousInput = currentInput;
            currentInput = '';
            operator = operation;
        }
    });

    operationButtons.appendChild(button);
}

// Calculer le résultat
document.getElementById('equalsBtn').addEventListener('click', () => {
    if (previousInput !== '' && currentInput !== '' && operator !== '') {
        const data = {
            num1: parseFloat(previousInput),
            num2: parseFloat(currentInput),
            operation: operator
        };

        fetch('http://localhost:8080/api/calculator/calculate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(result => {
            currentInput = result.toString();
            operator = '';
            previousInput = '';
            updateScreen();
        })
        .catch(error => console.error('Erreur:', error));
    }
});

// Réinitialiser l'écran
document.getElementById('clearBtn').addEventListener('click', () => {
    currentInput = '';
    previousInput = '';
    operator = '';
    updateScreen();
});

// Afficher un bouton d'opération (plugin) lorsque le bouton "Add Plugin" est cliqué
addPluginBtn.addEventListener('click', () => {
    // Récupérer les opérations disponibles depuis le backend
    fetch('http://localhost:8080/api/calculator/operations')
        .then(response => response.json())
        .then(operations => {
            // Ajouter les boutons d'opération
            operations.forEach(op => {
                addOperationButton(op);
            });
            // Désactiver le bouton "Add Plugin" après ajout des boutons
            addPluginBtn.disabled = true;
        })
        .catch(error => console.error('Erreur lors de la récupération des opérations:', error));
});
