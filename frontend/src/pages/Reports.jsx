import { useState } from 'react';
import { FileBarChart, Download } from 'lucide-react';
import { BASE } from '../services/api';

export default function Reports() {
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  async function downloadReport() {
    setError('');
    setLoading(true);
    try {
      const token = localStorage.getItem('cs_token');
      const response = await fetch(`${BASE}/reports/incidents.csv`, {
        headers: token ? { Authorization: `Bearer ${token}` } : {},
      });
      if (!response.ok) throw new Error(`Report download failed (${response.status})`);

      const url = URL.createObjectURL(await response.blob());
      const link = document.createElement('a');
      link.href = url;
      link.download = 'cybershield-incidents.csv';
      link.click();
      URL.revokeObjectURL(url);
    } catch (e) {
      setError(e.message || 'Could not download the report');
    } finally {
      setLoading(false);
    }
  }

  return <div className="reportHero">
    <div className="reportIcon"><FileBarChart size={34}/></div>
    <h2>Security Reports</h2>
    <p>Export the incident register for review, audit and offline analysis.</p>
    <button className="primary" onClick={downloadReport} disabled={loading}>
      <Download size={17}/>{loading ? 'Preparing report…' : 'Download incident CSV'}
    </button>
    {error && <div className="error" role="alert">{error}</div>}
    <div className="reportCards">
      <div><b>Incident report</b><span>CSV export of all tracked incidents, severity, priority, status and timestamps.</span></div>
      <div><b>Audit-ready data</b><span>Use the Activity Log screen to review accountability and investigation actions.</span></div>
    </div>
  </div>;
}
