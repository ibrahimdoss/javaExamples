package com.etiya.cvWorkNine.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.cvWorkNine.business.abstracts.CoverLetterService;
import com.etiya.cvWorkNine.core.utilities.DataResult;
import com.etiya.cvWorkNine.core.utilities.Result;
import com.etiya.cvWorkNine.core.utilities.SuccessDataResult;
import com.etiya.cvWorkNine.core.utilities.SuccessResult;
import com.etiya.cvWorkNine.dataAccess.abstracts.CoverLetterDao;
import com.etiya.cvWorkNine.dataAccess.abstracts.JobSeekerDao;
import com.etiya.cvWorkNine.entities.concretes.CoverLetter;

@Service
public class CoverLetterManager implements CoverLetterService{
	
	private CoverLetterDao coverLetterDao;
	private JobSeekerDao jobSeekerDao;
	
	@Autowired
	public CoverLetterManager(CoverLetterDao coverLetterDao, JobSeekerDao jobSeekerDao) {
		super();
		this.coverLetterDao = coverLetterDao;
		this.jobSeekerDao=jobSeekerDao;
	}

	@Override
	public DataResult<List<CoverLetter>> getAll() {
		return new SuccessDataResult<List<CoverLetter>>(this.coverLetterDao.findAll());
	}

	@Override
	public DataResult<CoverLetter> getById(int id) {
		return new SuccessDataResult<CoverLetter>(this.coverLetterDao.findById(id).get());
	}

	@Override
	public Result add(int id, CoverLetter coverLetter) {
		coverLetter.setJobSeeker(this.jobSeekerDao.findById(id).get());
		this.coverLetterDao.save(coverLetter);
		return new SuccessResult("ÖnYazı Eklendi");
	}

	@Override
	public Result update(CoverLetter coverLetter) {
		this.coverLetterDao.save(coverLetter);
		return new SuccessResult("Ön Yazı Güncellendi");
	}

	@Override
	public Result delete(int id) {
		this.coverLetterDao.deleteById(id);
		return new SuccessResult("Ön Yazı Silindi");
	}
	
}
