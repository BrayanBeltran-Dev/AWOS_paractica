import { useState, useEffect } from 'react'

const API = 'http://localhost:8080/api/productos'

function App() {
  const [nombre, setNombre] = useState('')
  const [precio, setPrecio] = useState('')
  const [cantidad, setCantidad] = useState('')
  const [productos, setProductos] = useState([])
  const [error, setError] = useState('')
  const [editandoId, setEditandoId] = useState(null)

  useEffect(() => {
    listar()
  }, [])

  const listar = async () => {
    const response = await fetch(`${API}/listar`)
    const data = await response.json()
    setProductos(data)
  }

  const validar = () => {
    if (!nombre || !precio || !cantidad) {
      setError('Todos los campos son obligatorios')
      return false
    }
    if (Number(precio) <= 0) {
      setError('El precio debe ser mayor a 0')
      return false
    }
    if (Number(cantidad) < 0) {
      setError('La cantidad no puede ser negativa')
      return false
    }
    setError('')
    return true
  }

  const registrar = async () => {
    if (!validar()) return
    await fetch(`${API}/registrar`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ nombre, precio: Number(precio), cantidad: Number(cantidad) })
    })
    limpiar()
    listar()
  }

  const actualizar = async () => {
    if (!validar()) return
    await fetch(`${API}/actualizar/${editandoId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ nombre, precio: Number(precio), cantidad: Number(cantidad) })
    })
    limpiar()
    listar()
  }

  const eliminar = async (id) => {
    await fetch(`${API}/eliminar/${id}`, { method: 'DELETE' })
    listar()
  }

  const cargarParaEditar = (producto) => {
    setEditandoId(producto.id)
    setNombre(producto.nombre)
    setPrecio(producto.precio)
    setCantidad(producto.cantidad)
    setError('')
  }

  const limpiar = () => {
    setNombre('')
    setPrecio('')
    setCantidad('')
    setError('')
    setEditandoId(null)
  }

  return (
    <div className="container mt-5">
      <h1 className="mb-4">Gestión de Productos</h1>

      {/* Formulario */}
      <div className="card mb-4">
        <div className="card-header">
          {editandoId ? '✏️ Editar Producto' : '➕ Nuevo Producto'}
        </div>
        <div className="card-body">
          <div className="row g-3">
            <div className="col-md-4">
              <input className="form-control" placeholder="Nombre"
                value={nombre} onChange={(e) => setNombre(e.target.value)} />
            </div>
            <div className="col-md-4">
              <input type="number" className="form-control" placeholder="Precio"
                value={precio} onChange={(e) => setPrecio(e.target.value)} />
            </div>
            <div className="col-md-4">
              <input type="number" className="form-control" placeholder="Cantidad"
                value={cantidad} onChange={(e) => setCantidad(e.target.value)} />
            </div>
          </div>

          {error && <p className="text-danger mt-2 mb-0">{error}</p>}

          <div className="mt-3">
            {editandoId ? (
              <>
                <button className="btn btn-warning me-2" onClick={actualizar}>Guardar cambios</button>
                <button className="btn btn-secondary" onClick={limpiar}>Cancelar</button>
              </>
            ) : (
              <button className="btn btn-primary" onClick={registrar}>Registrar</button>
            )}
          </div>
        </div>
      </div>

      {/* Tabla */}
      <table className="table table-bordered table-hover">
        <thead className="table-dark">
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {productos.length === 0 ? (
            <tr>
              <td colSpan="5" className="text-center text-muted">No hay productos registrados</td>
            </tr>
          ) : (
            productos.map(p => (
              <tr key={p.id}>
                <td>{p.id}</td>
                <td>{p.nombre}</td>
                <td>${p.precio}</td>
                <td>{p.cantidad}</td>
                <td>
                  <button className="btn btn-warning btn-sm me-2" onClick={() => cargarParaEditar(p)}>Editar</button>
                  <button className="btn btn-danger btn-sm" onClick={() => eliminar(p.id)}>Eliminar</button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  )
}

export default App
