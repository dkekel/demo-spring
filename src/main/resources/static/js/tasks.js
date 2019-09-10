const addSaveTaskHandler = () => {
    Array.from(document.getElementsByClassName('save-task')).forEach(function (saveButton) {
        hideActionButton(saveButton);
        Array.from(saveButton.parentElement.getElementsByTagName('input')).forEach(function (checkbox) {
            checkbox.onclick = function (event) {
                const formAction = saveButton.formAction;
                const formMethod = saveButton.parentElement.method;
                const formData = new FormData(saveButton.parentElement);
                checkbox.nextElementSibling.className = event.target.checked ? 'checked' : '';
                const request = new XMLHttpRequest();
                request.open(formMethod, formAction, true);
                request.send(formData);
                updateTaskList();
            };
        });
    });
};

const addFilterTasksHandler = () => {
    const filterButton = document.getElementById('filterTasks');
    const completedFilter = getCompletedFilter();
    hideActionButton(filterButton);
    completedFilter.onclick = function () {
        updateTaskList();
    }
};

const updateTaskList = () => {
    const completedFilter = getCompletedFilter();
    const hideCompleted = completedFilter.checked;
    const taskList = document.getElementById('taskList');
    const tasks = taskList.getElementsByTagName("li");
    for (let i = 0; i < tasks.length; ++i) {
        const taskFields = tasks[i].getElementsByTagName('input');
        const checkbox = Array.from(taskFields).filter(input => input.name === 'done')[0];
        if (checkbox !== undefined && checkbox.checked === true) {
            tasks[i].hidden = hideCompleted;
        }
    }
};

const getCompletedFilter = () => {
    return  document.getElementById('hideCompleted');
};

const hideActionButton = (element) => {
    element.style.display = 'none';
    element.onclick = function (event) {
        event.preventDefault();
    };
};

window.addEventListener('load', addSaveTaskHandler, false );
window.addEventListener('load', addFilterTasksHandler, false );
