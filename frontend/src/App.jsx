import { lazy, Suspense } from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import { useAuth } from './context/AuthContext';
import Layout from './components/Layout';

const Login = lazy(() => import('./pages/Login'));
const Register = lazy(() => import('./pages/Register'));
const Dashboard = lazy(() => import('./pages/Dashboard'));
const Incidents = lazy(() => import('./pages/Incidents'));
const IncidentDetails = lazy(() => import('./pages/IncidentDetails'));
const NewIncident = lazy(() => import('./pages/NewIncident'));
const Users = lazy(() => import('./pages/Users'));
const Activity = lazy(() => import('./pages/Activity'));
const Reports = lazy(() => import('./pages/Reports'));

function Private({ children }) {
  const { user } = useAuth();
  return user ? <Layout>{children}</Layout> : <Navigate to="/login" replace/>;
}

export default function App() {
  return <Suspense fallback={<div className="loading">Loading CyberShield…</div>}>
    <Routes>
      <Route path="/login" element={<Login/>}/>
      <Route path="/register" element={<Register/>}/>
      <Route path="/" element={<Private><Dashboard/></Private>}/>
      <Route path="/incidents" element={<Private><Incidents/></Private>}/>
      <Route path="/incidents/new" element={<Private><NewIncident/></Private>}/>
      <Route path="/incidents/:id" element={<Private><IncidentDetails/></Private>}/>
      <Route path="/users" element={<Private><Users/></Private>}/>
      <Route path="/activity" element={<Private><Activity/></Private>}/>
      <Route path="/reports" element={<Private><Reports/></Private>}/>
      <Route path="*" element={<Navigate to="/" replace/>}/>
    </Routes>
  </Suspense>;
}
