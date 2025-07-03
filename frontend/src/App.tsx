import { BrowserRouter, Route, Routes } from "react-router"
import LandingPage from "./pages/common/LandingPage"
import Signup from "./pages/common/Signup"
import Login from "./pages/common/Login"
import AuthLayout from "./components/layouts/AuthLayout"
import PatientLayout from "./components/layouts/PatientLayout"
import PatientDashboard from "./pages/patient/PatientDashboard"
import PatientProfile from "./pages/patient/PatientProfile"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LandingPage />} />

        <Route path="/auth" element={<AuthLayout />}>
          <Route path="login" element={<Login />} />
          <Route path="signup" element={<Signup />} />
        </Route>

        <Route path="/patient" element={<PatientLayout />}>
          <Route path="dashboard" element={<PatientDashboard />}/>
          <Route path="profile" element={<PatientProfile />}/>
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App