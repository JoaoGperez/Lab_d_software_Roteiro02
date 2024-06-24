import React, { useState } from 'react'

export const TodoForm = ({ addTodo }) => {

    const [description, setDescription] = useState('');
    const [dueDate, setDueDate] = useState('');
    const [priority, setPriority] = useState('');


    const handleSubmit = (e) => {
        e.preventDefault();
        if (description) {
            addTodo(description, dueDate, priority); // adicionar tarefa
            setDescription(''); // limpar formulário apos envio
            setDueDate('');
            setPriority('Medio');
        }
    };

    return (
        <form className="TodoForm" onSubmit={handleSubmit} >
            
                <input type="text"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    className="todo-input"
                    placeholder='Descrição da Tarefa'
                />
                <div className='form-group'>
                <input type="date"
                    value={dueDate}
                    onChange={(e) => setDueDate(e.target.value)}
                    className='todo-input'
                    placeholder='Data de Vencimento'
                />
                <select
                    value={priority}
                    onChange={(e) => setPriority(e.target.value)}
                    className='todo-select'>
                    <option value="High">Alta</option>
                    <option value="Medium">Media</option>
                    <option value="Low">Baixa</option>
                </select>
            </div>

            <button type="submit" className='todo-btn'>Adicionar Tarefa</button>
        </form>
    )
}