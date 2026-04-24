import { useState } from 'react'

function App() {
  const [num1, setNum1] = useState('');
  const [num2, setNum2] = useState('');
  const [operacion, setOperacion] = useState('sumar');
  const [resultado, setResultado] = useState(null);

  const calcular = async () => {
    const response = await fetch('http://localhost:8080/api/calculadora/calcular',{
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({num1, num2, operacion})
    })
    const data = await response.json()
    setResultado(data.resultado)
  }
  const limpiar = () => {
      setNum1('')
      setNum2('')
      setOperacion('sumar')
      setResultado(null)
  }
  return (
      <div className="container-md text-center mt-5 justify-content-center">
        <h1 className="mb-4">Calculadora</h1>
        <div className="row g-3">
          <div className="col">
            <input type="number" className="form-control" placeholder="Ingrese un numero"
            value={num1} onChange={(e) => setNum1(e.target.value)} />
          </div>
          <div className="col">
            <select className="form-select"
              value={operacion} onChange={(e) => setOperacion(e.target.value)}>
              <option value="sumar">Suma</option>
              <option value="restar">Resta</option>
              <option value="multiplicar">Multiplicacion</option>
              <option value="dividir">Division</option>
            </select>
          </div>
          <div className="col">
            <input type="number" className="form-control" placeholder="Ingrese un numero"
            value={num2} onChange={(e) => setNum2(e.target.value)} />
          </div>
        </div>
        <button className="btn btn-primary mb-4 mt-4" onClick={calcular}>
          Calcular
        </button>
        <button className="btn btn-primary mb-4 mt-4 m-3" onClick={limpiar}>
          Limpiar
        </button>
        <h2>Resultado: {resultado}</h2>
      </div>
  )
}

export default App
