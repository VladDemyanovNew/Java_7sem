let referencesSource = [];

const referencesContainer = document.getElementById('references-container');
const insertContainer = document.getElementById('insert-container');
const insertUrlTag = document.getElementById('insertUrl');
const insertDescriptionTag = document.getElementById('insertDescription');

const sessionId = document.getElementById('sessionId').value;
const secretMessage = '123456';
let isOwnerMode = false;

(async () => {
    await loadReferences();
})();

async function loadReferences() {
    const response = await fetch('ReferencesServlet');
    const references = await response.json();

    referencesSource = references.sort((x, y) => y.Id - x.Id);

    referencesContainer.innerHTML = referencesSource
        .sort((x, y) => y.Id - x.Id)
        .map(x => createReferenceView(x))
        .join('');

    setControlsVisibility();
}

function createReferenceView(reference) {
    const deleteInfoContainer = `<div id="delete-info-container-${reference.Id}" class="container" hidden><span>${reference.Id} ${reference.Description}</span><br>` +
        `<button onclick="submitDeletingReference(${reference.Id})">OK</button><button onclick="closeDeletingReference(${reference.Id})">Cancel</button></div>`;

    const updateInfoContainer = `<div id="update-info-container-${reference.Id}" class="container" hidden><input type="text" id="updateUrl${reference.Id}" value="${reference.Url}"><br>` +
        `<input type="text" id="updateDescription${reference.Id}" value="${reference.Description}"><br>` +
        `<button onclick="submitUpdatingReference(${reference.Id})">OK</button><button onclick="closeUpdatingReference(${reference.Id})">Cancel</button></div>`;

    const commentsContainer = `<div id="comments-container-${reference.Id}" class="container" hidden><button onclick="onInsertCommentBtnClick(${reference.Id})">insert</button><br>` +
        `<div class="container" id="insert-comment-container-${reference.Id}" hidden>` +
        `<textarea id="insertedCommentText${reference.Id}"></textarea><br><button onclick="submitCommentInserting(${reference.Id})">OK</button><button onclick="closeInsertCommentContainer(${reference.Id})">Cancel</button></div>` +
        `<div id="comments-data-container-${reference.Id}"></div></div>`;

    return `<div class='container'><button class='ml deleteRefControl' onclick='onDeleteReferenceBtnClick(${reference.Id})'>delete</button>` +
        `<button class='ml updateRefControl' onclick='onUpdateReferenceBtnClick(${reference.Id})'>update</button>` +
        `<button class='ml plusRefControl'>+${reference.Plus}</button><button class='ml minusRefControl'>-${reference.Minus}</button>` +
        `<button class='ml' onclick='onCommentBtnClick(${reference.Id})'>comments</button>` +
        `<span class='ml'>[${reference.Id}]</span><a class='ml' href='${reference.Url}'>${reference.Description}</a>` +
        `${deleteInfoContainer}${updateInfoContainer}${commentsContainer}</div>`;
}

function createCommentView(comment) {
    let actions = '';
    if (sessionId === comment.SessionId || isOwnerMode) {
        actions = `<button>update</button>` +
            `<button onclick="deleteComment(${comment.Id}, ${comment.ReferenceId})">delete</button>`;
    }

    return `<div class="container"><span>Stamp: ${comment.Stamp}</span><br>` + actions +
        `<br><textarea readonly value="${comment.Text}">${comment.Text}</textarea></div>`;
}

function onInsertBtnClick() {
    insertContainer.hidden = false;
}

function closeInsertingReference() {
    insertContainer.hidden = true;
}

function rebuildReferences() {
    referencesContainer.innerHTML = referencesSource
        .sort((x, y) => y.Id - x.Id)
        .map(x => createReferenceView(x)).join('');
}

async function submitInserting() {
    const referenceCreateData = {
        Id: 0,
        Url: insertUrlTag.value,
        Description: insertDescriptionTag.value,
        Minus: 0,
        Plus: 0,
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(referenceCreateData),
    };
    const response = await fetch('ReferencesServlet', options);
    const reference = await response.json();

    referencesSource.unshift(reference);
    rebuildReferences();

    closeInsertingReference();
}

async function submitDeletingReference(id) {
    const response = await fetch(`ReferencesServlet?id=${id}`, { method: 'DELETE' });
    if (response.ok) {
        const referenceIndex = referencesSource.findIndex(x => x.Id === id);
        referencesSource.splice(referenceIndex, 1);
        rebuildReferences();
    }
}

function closeDeletingReference(id) {
    document.getElementById(`delete-info-container-${id}`).hidden = true;
}

function onDeleteReferenceBtnClick(id) {
    document.getElementById(`delete-info-container-${id}`).hidden = false;
}

async function submitUpdatingReference(id) {
    const updatedUrl = document.getElementById(`updateUrl${id}`).value;
    const updatedDescription = document.getElementById(`updateDescription${id}`).value;

    const reference = referencesSource.find(x => x.Id === id);
    const referenceUpdateData = {
        Id: id,
        Url: updatedUrl,
        Description: updatedDescription,
        Minus: reference.Minus,
        Plus: reference.Plus,
    };

    const options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(referenceUpdateData),
    };
    const response = await fetch(`ReferencesServlet?id=${id}`, options);
    if (response.ok) {
        const referenceIndex = referencesSource.findIndex(x => x.Id === id);

        referencesSource.splice(referenceIndex, 1, referenceUpdateData);
        rebuildReferences();
        closeUpdatingReference(id);
    }
}

function closeUpdatingReference(id) {
    document.getElementById(`update-info-container-${id}`).hidden = true;
}

function onUpdateReferenceBtnClick(id) {
    document.getElementById(`update-info-container-${id}`).hidden = false;
}

async function onCommentBtnClick(referenceId) {
    const commentsContainer = document.getElementById(`comments-container-${referenceId}`);
    if (!commentsContainer.hidden) {
        commentsContainer.hidden = true;
        return;
    }

    commentsContainer.hidden = false;

    await loadComments(referenceId);
}

async function loadComments(referenceId) {
    const commentsDataContainer = document.getElementById(`comments-data-container-${referenceId}`);

    const response = await fetch(`CommentsServlet?referenceId=${referenceId}`);
    const comments = await response.json();

    commentsDataContainer.innerHTML = comments
        .sort((x, y) => y.Id - x.Id)
        .map(x => createCommentView(x))
        .join('');
}

function onInsertCommentBtnClick(referenceId) {
    document.getElementById(`insert-comment-container-${referenceId}`).hidden = false;
}

function closeInsertCommentContainer(referenceId) {
    document.getElementById(`insert-comment-container-${referenceId}`).hidden = true;
}

async function submitCommentInserting(referenceId) {
    const text = document.getElementById(`insertedCommentText${referenceId}`).value;
    const insertedComment = {
        Id: 0,
        ReferenceId: referenceId,
        SessionId: sessionId,
        Stamp: convertToSqlDate(new Date()),
        Text: text,
    };

    const option = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(insertedComment),
    };

    const response = await fetch('CommentsServlet', option);
    await response.json();

    closeInsertCommentContainer(referenceId);
    await loadComments(referenceId);
}

async function deleteComment(id, referenceId) {
    const response = await fetch(`CommentsServlet?id=${id}`, { method: 'DELETE' });
    if (response.ok) {
        await loadComments(referenceId);
    }
}

function convertToSqlDate(date) {
    return date.getUTCFullYear() + '-' +
        ('00' + (date.getUTCMonth() + 1)).slice(-2) + '-' +
        ('00' + date.getUTCDate()).slice(-2);
}

function setOwnerMode() {
    const insertedSecretMessage = document.getElementById('secret-message').value;
    isOwnerMode = insertedSecretMessage === secretMessage;
    setControlsVisibility();
}

function resetOwnerMode() {
    isOwnerMode = false;
    setControlsVisibility();
}

function setControlsVisibility() {
    const insertRefControl = document.getElementById('insertRefControl');
    const deleteRefControls = Array.from(document.getElementsByClassName('deleteRefControl'));
    const updateRefControls = Array.from(document.getElementsByClassName('updateRefControl'));
    const plusRefControls = Array.from(document.getElementsByClassName('plusRefControl'));
    const minusRefControls = Array.from(document.getElementsByClassName('minusRefControl'));

    insertRefControl.hidden = !isOwnerMode;
    deleteRefControls.forEach(x => x.hidden = !isOwnerMode);
    updateRefControls.forEach(x => x.hidden = !isOwnerMode);
    plusRefControls.forEach(x => x.hidden = !isOwnerMode);
    minusRefControls.forEach(x => x.hidden = !isOwnerMode);
}