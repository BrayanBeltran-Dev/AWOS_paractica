import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'

function App() {
  const [nombre, setNombre] = useState('')
  const [apellido, setApellido] = useState('')
  const [edad, setEdad] = useState('')

  const [estudiantes, setEstudiantes] = useState([])
  const [error, setError] = useState('')
  const listar = async () => {
    const response = await fetch(`http://localhost:8080/api/estudiantes`)
    const data = await response.json()
    setEstudiantes(data)
  }

  const registrar = async () => {
    if(!nombre || !apellido || !edad) {
      setError('Todos los campos son obligatorios')
      return
    }
    if(edad < 18){
      setError('La edad debe ser mayor a 18')
      return
    }
    setError('')

     await fetch('http://localhost:8080/api/estudiantes',{
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({nombre, apellido, edad}),
    })
    listar()
  }
  /*
  useEffect(() => {
    listar()
  },[])
*/
  const limpar = () => {
    setNombre('')
    setApellido('')
    setEdad('')
    setEstudiantes([''])
    setError('')
  }

  return (
      <div className="container-mt-5 text-center justify-content-center">
        <h1 className="mb-4 mt-4">Estudiante</h1>
        <div className="mb-3">
          <input className="form-control" placeholder="Nombre"
                 value={nombre} onChange={(e) => setNombre(e.target.value)} />
        </div>
        <div className="mb-3">
          <input className="form-control" placeholder="Apellido"
                 value={apellido} onChange={(e) => setApellido(e.target.value)} />
        </div>
        <div className="mb-3">
          <input type="number" className="form-control" placeholder="Edad"
                 value={edad} onChange={(e) => setEdad(e.target.value)} />
        </div>
        <p className="text-danger mt-4">{error}</p>
        <button className="btn btn-primary mb-4" onClick={registrar}>
          Registrar
        </button>

        <button className="btn btn-primary mb-4 ms-5" onClick={listar}>
          Listar
        </button>

        <button className="btn btn-primary mb-4 ms-5" onClick={limpar}>
          Limpiar
        </button>

        <table className="table text-center">
          <thead>
          <tr>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Edad</th>
          </tr>
          </thead>
          <tbody>
          {estudiantes.map(e => (
              <tr key={e.id}>
                <td>{e.nombre}</td>
                <td>{e.apellido}</td>
                <td>{e.edad}</td>
              </tr>
          ))}
          </tbody>
        </table>
      </div>
  )
}

export default App
