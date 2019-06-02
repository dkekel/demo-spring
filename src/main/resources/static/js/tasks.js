window.addEventListener('load', addSaveTaskHandler, false );

function addSaveTaskHandler() {
    Array.from(document.getElementsByClassName('save-task')).forEach(function (saveButton) {
        saveButton.style.display = 'none';
        saveButton.onclick = function (event) {
            event.preventDefault();
        };
        Array.from(saveButton.parentElement.getElementsByTagName('input')).forEach(function (checkbox) {
            checkbox.onclick = function (event) {
                var formAction = saveButton.formAction;
                var formMethod = saveButton.parentElement.method;
                var formData = new FormData(saveButton.parentElement);
                checkbox.nextElementSibling.className = event.target.checked ? 'checked' : '';
                var request = new XMLHttpRequest();
                request.open(formMethod, formAction, true);
                request.send(formData);
            };
        });
    });
}
