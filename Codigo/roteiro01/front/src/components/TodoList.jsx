import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import React from 'react';

export const TodoList = ({ task, deleteTodo, editTodo, toggleComplete }) => {
    return (
        <div className="Todo">
            <div className='task-info'>
            <p 
                className={`${task.completed ? "completed" : "incompleted"}`} 
                onClick={() => toggleComplete(task.id)}
            >
                {task.description}
            </p>
            <p className="due-date">Vencimento: {task.dueDate}</p>
                <p className={`priority ${task.priority.toLowerCase()}`}>Prioridade: {task.priority}</p>

            </div>

            <div className="todo-buttons">
                <button onClick={() => deleteTodo(task.id)}>
                    <FontAwesomeIcon icon={faTrash} />
                </button>
                <button onClick={() => editTodo(task.id)}>
                    <FontAwesomeIcon className="edit-icon" icon={faPenToSquare} />
                </button>
            </div>
        </div>
    );
};
